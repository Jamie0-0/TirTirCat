package order.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import core.util.HibernateUtil;
import member.dao.MemberDao;
import member.dao.MemberDaoImpl;
import order.dao.ProductOrderDao;
import order.dao.ProductOrderDaoImpl;
import order.dao.SubOrderDao;
import order.dao.SubOrderDaoImpl;
import order.dao.SubProductDao;
import order.dao.SubProductDaoImpl;
import order.vo.CartItem;
import order.vo.Orders;
import order.vo.ProductOrder;
import order.vo.SubOrder;
import order.vo.SubProduct;
import product_fe.dao.ProductDao;
import product_fe.dao.ProductDaoImpl;
import product_fe.dao.ProductUserDao;
import product_fe.dao.ProductUserDaoImpl;
import product_fe.util.ProductUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import product_fe.util.JedisPoolUtil;

public class ProductOrderServiceImpl implements ProductOrderService {
	private ProductOrderDao productOrderDao;
	private SubOrderDao subOrderDao;
	private SubProductDao subProductDao;

	private ProductDao productDao;
	private ProductUserDao productUserDao;

	private List<String> msgs;

	public ProductOrderServiceImpl() {
		productOrderDao = new ProductOrderDaoImpl();
		subOrderDao = new SubOrderDaoImpl();
		subProductDao = new SubProductDaoImpl();
		productDao = new ProductDaoImpl();
		msgs = new LinkedList<String>();
		productUserDao = new ProductUserDaoImpl();
	}

	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@Override
	public boolean createOrders(Orders order) {
		msgs.clear();

		ProductOrder productOrder = order.getProductOrder();
		List<CartItem> cartItems = order.getCartlist();
		List<SubOrder> subOrders = new ArrayList();
		boolean orderIsEstabished;

		// 先判斷錯誤處理
		if (productOrder.getOrder_r_name().isEmpty()) {
			msgs.add("收件人姓名不可為空");
		}
		if (productOrder.getOrder_r_phone().isEmpty()) {
			msgs.add("連絡電話不可為空");
		}
		if (productOrder.getOrder_r_addr().isEmpty()) {
			msgs.add("收件地址不可為空");
		}
		if (!msgs.isEmpty()) {
			return orderIsEstabished = false;
		}

		
		/////// 依照 p_m_id 來分組購物車項目，建立子訂單和子訂單明細
		// Map<Integer 廠商id, SubOrder 廠商子訂單> -> 先篩選購物車內商品中的廠商id, 並用廠商id分出會有幾個子訂單
		Map<Integer, SubOrder> mIdAndSubOrderMap = new HashMap<>();
		for (CartItem cartItem : cartItems) {
			if (!mIdAndSubOrderMap.containsKey(cartItem.getItem_p_m_id())) {
				int p_m_id = cartItem.getItem_p_m_id();
				SubOrder subOrder = new SubOrder();
				subOrder.setSo_m_id(p_m_id);

				subOrders.add(subOrder);
				mIdAndSubOrderMap.put(p_m_id, subOrder);

				System.out.println("Map<Integer, SubOrder> mIdAndSubOrderMap = " + mIdAndSubOrderMap);
			}
//			subOrders = new ArrayList<>(mIdAndSubOrderMap.values());
			System.out.println("ArrayList<SubOrder> subOrders = " + subOrders);
		}

		/////// 找出每個廠商子訂單中所對應的子訂單明細
		// 每個廠商子訂單個別拿出
		for (SubOrder subOrder : subOrders) {
			int order_num = 0; // 每個廠商子訂單總金額
			// new一個某廠商子訂單底下的子訂單明細list, list中裝有該廠商的個別商品明細 (子訂單明細)
			List<SubProduct> subProducts = new ArrayList<>();
			// 每一種購物車商品
			for (CartItem cartItem : cartItems) {
				int item_m_id = cartItem.getItem_p_m_id();
				// 當購物車商品的廠商id = 子訂單廠商id -> 該商品來自該廠商, 將商品資訊放入該廠商的子訂單明細list
				if (item_m_id == subOrder.getSo_m_id()) {
					item_m_id = cartItem.getItem_p_m_id();
					System.out.println("item_m_id =" + item_m_id);
					SubProduct subProduct = new SubProduct();
					int item_quantity = cartItem.getItem_quantity();
					int item_p_price = cartItem.getItem_p_price();
					subProduct.setP_p_id(cartItem.getItem_p_id());
					subProduct.setP_m_stock(item_quantity);
					subProduct.setP_m_price(item_p_price);

					order_num += item_quantity * item_p_price;

					subProducts.add(subProduct);
					System.out.println("每一筆子訂單明細subProduct =" + subProduct);
				}
				subOrder.setOrder_num(order_num); // 子訂單金額加總並放回
			}
			System.out.println("每個廠商的子訂單List<SubProduct> subProducts中的子訂單明細們 = " + subProducts);
			// 在某廠商子訂單中, 存入該子訂單對應的商品明細list
			subOrder.setSubProducts(subProducts);

			subOrder.setOrder_status("0"); // 子訂單出貨狀態設為0.未出貨

		}

		productOrder.setOrder_s("0"); // 母訂單出貨狀態設為0.未出貨
		productOrder.setOrder_pay("1"); // 母訂單付款狀態設為1.已付款

		System.out.println("productOrder = " + productOrder);
		System.out.println("subOrders = " + subOrders);

		Transaction transaction = getSession().getTransaction();
		try {
			transaction.begin();
			int order_id = productOrderDao.insertProductOrder(productOrder); // 母訂單編號
			for (SubOrder subOrder : subOrders) {
				subOrder.setSo_order_num(order_id); // 母訂單編號指定給每個廠商子訂單
				int so_order_num = subOrderDao.insertSubOrder(subOrder); // 生成子訂單編號

				List<SubProduct> subProducts = subOrder.getSubProducts();
				for (SubProduct subProduct : subProducts) {
					subProduct.setOrder_id(so_order_num); // 子訂單編號指定給每個子訂單明細
					subProductDao.insertSubProduct(subProduct);

					// 更新廠商商品數量 -> 商品數量 = 原數量 - 購買數量
					productDao.updatePStockByPid(subProduct.getP_p_id(), subProduct.getP_m_stock());
				}
			}
			transaction.commit();
			msgs.add("訂單已成立，3秒後將自動跳轉至商城首頁。");
			return orderIsEstabished = true;

		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			msgs.add("結帳失敗，請重試或聯繫服務人員。");
			return orderIsEstabished = false;
		}

	}

	@Override
	public List<String> getMsgs() {
		return msgs;
	}

	@Override
	public void deleteCartFromRedis(HttpSession session) {

		try {
			String username = (String) session.getAttribute("username");
			int uid = productUserDao.selectByUserNameForCart(username).getUid();
			String u_id = String.valueOf(uid);

			productOrderDao.deleteKeys(u_id);

			HashMap<Integer, Integer> cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");
			cartList.clear();

		} catch (JedisConnectionException e) {
			e.printStackTrace();
			System.out.println("Redis連線失敗");
		} catch (Exception e) {
			System.out.println("非Redis錯誤");
		}

	}

//	@Override
//	public boolean createOrders(ProductOrder productOrder, SubOrder subOrder, SubProduct subProduct) {
//		// 測試用
//		Transaction transaction = getSession().getTransaction();
//		try {
//			transaction.begin();
//			int order_id = productOrderDao.insertProductOrder(productOrder); // 母訂單編號
//			subOrder.setSo_order_num(order_id); // 正確設置so_order_num
//			int so_order_num = subOrderDao.insertSubOrder(subOrder); // 子訂單編號
//			subProduct.setOrder_id(so_order_num);
//			subProductDao.insertSubProduct(subProduct);
//			transaction.commit();
//		} catch (Exception e) {
//			transaction.rollback();
//			e.printStackTrace();
//		}
//		return true;
//	}

}
