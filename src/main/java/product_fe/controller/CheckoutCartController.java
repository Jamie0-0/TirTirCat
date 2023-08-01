package product_fe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import core.util.HibernateUtil;
import order.vo.Orders;
import order.vo.ProductOrder;
import product_fe.service.ProductService;
import product_fe.service.ProductServiceImpl;

@WebServlet("/checkoutCart")
public class CheckoutCartController extends HttpServlet {

	private ProductService service;

	@Override
	public void init() throws ServletException {
		service = new ProductServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		// 1. 清空購物車
		// 2. 生成母訂單, 子訂單, 訂單明細 (訂單狀態: 預設未出貨?)
		// 3. 商品數量跟著購買數量減少
		// 4. 全部交易控制

		Orders order = gson.fromJson(req.getReader(), Orders.class);

//		Session session = HibernateUtil.getSessionFactory().openSession();
//		ProductOrder productOrder = session.get(ProductOrder.class, 1);
//		System.out.println(productOrder.getOrder_r_name());
//		HibernateUtil.shutdown();

//		ProductOrder productOrder = gson.fromJson(req.getReader(), ProductOrder.class);
//		String r_name = productOrder.getOrder_r_name();
//		System.out.println(r_name);
//      JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);
//      String r_name = jsonObject.get("r_name").getAsString();
		System.out.println(order);
//		String r_name = order.getProductOrder().getOrder_r_name();
//		System.out.println(r_name);
		ProductOrder productOrder = order.getProductOrder();
		JsonArray cartlist = order.getCartlist();
		System.out.println("productOrder = " + productOrder);
		System.out.println("cartlist = " + cartlist);
//      System.out.println("JSON to JSONArray: " + jsonObject);

		// 回應成功訊息到前端
		String responseJSON = gson.toJson("訂單提交成功");
		resp.getWriter().write(responseJSON);

//     // 使用 Gson 來解析 JSON 資料
//        JsonObject jsonObject = JsonParser.parseReader(req.getReader()).getAsJsonObject();
//        System.out.println("jsonObject" + jsonObject);
//
//        // 現在可以從 jsonObject 中獲取前端傳來的資料
//        String order_r_name = jsonObject.get("order_r_name").getAsString();
//        String order_r_phone = jsonObject.get("order_r_phone").getAsString();
//        String order_r_address = jsonObject.get("order_r_address").getAsString();
//        int order_dfee = jsonObject.get("order_dfee").getAsInt();
//        int order_t = jsonObject.get("order_t").getAsInt();
//        
//        System.out.println(order_r_name);
//        System.out.println(order_r_phone);
//        System.out.println(order_r_address);
//        System.out.println(order_dfee);
//        System.out.println(order_t);
//
//        // 獲取 cartList 資料
//        JsonArray cartListJsonArray = jsonObject.get("cartList").getAsJsonArray();
//
//        // 現在可以遍歷 cartListJsonArray 並獲取每個 cartItem 的資料
//        for (int i = 0; i < cartListJsonArray.size(); i++) {
//            JsonObject cartItemJsonObject = cartListJsonArray.get(i).getAsJsonObject();
//            String p_id = cartItemJsonObject.get("p_id").getAsString();
//            int quantity = cartItemJsonObject.get("quantity").getAsInt();
//            int p_price = cartItemJsonObject.get("p_price").getAsInt();
//            
//            
//            System.out.println(p_id);
//            System.out.println(quantity);
//            System.out.println(p_price);
//        }

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
