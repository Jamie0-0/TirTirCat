package order.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.Transaction;

import core.util.HibernateUtil;
import order.vo.ProductOrder;

public class ProductOrderDaoImpl implements ProductOrderDao {
	private DataSource ds;
	
	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

//	public ProductOrderDaoImpl() {
//		try {
//			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FurrEver");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}

	@Override
	public int insertProductOrder(ProductOrder productOrder) {
		getSession().persist(productOrder);
		return productOrder.getOrder_id();
//				 Session session = getSession();
//		    Transaction transaction = null;
//
//			try {
//				Transaction tx = session.beginTransaction();
//				session.persist(productOrder);
//				tx.commit();
//				return productOrder.getOrder_id();
//			} catch (Exception e) {
//				e.printStackTrace();
//				session.getTransaction().rollback();			
//			}
//
//			return -1;
//		    

//		String insertSql = "INSERT INTO product_order (order_uid, order_r_name, order_r_phone, order_r_addr, order_dfee, order_t, order_s, order_pay)\r\n"
//				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
//		int rowCount = 0;
//
//		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(insertSql)) {
//			ps.setInt(1, productOrder.getOrder_id());
//			ps.setInt(2, productOrder.getOrder_uid());
//			ps.setString(3, productOrder.getOrder_r_name());
//			ps.setString(4, productOrder.getOrder_r_phone());
//			ps.setString(5, productOrder.getOrder_r_addr());
//			ps.setInt(6, productOrder.getOrder_dfee());
//			ps.setInt(7, productOrder.getOrder_t());
//			ps.setString(8, productOrder.getOrder_s());
//			ps.setString(9, productOrder.getOrder_pay());
//			ps.setInt(10, productOrder.getGm_id());
//			ps.setTimestamp(11, productOrder.getGm_date());
//			ps.setString(12, productOrder.getOrder_1());
//			ps.setString(13, productOrder.getOrder_2());
//			ps.setString(14, productOrder.getOrder_3());
//
//			rowCount = ps.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return rowCount;
	}
	
	@Override
	public List<ProductOrder> selectAll() {
		return getSession().createQuery("from ProductOrder", ProductOrder.class).list();
	}

	@Override
	public ProductOrder selectByUid(int order_uid) {
	    Session session = getSession();
	    Transaction transaction = null;
	    ProductOrder productOrder = null;
//		return getSession().get(ProductOrder.class, order_uid);
//		return HibernateUtil.getSessionFactory().openSession().get(ProductOrder.class, order_uid);
	    
	    try {
	        transaction = session.beginTransaction();
	        productOrder = session.get(ProductOrder.class, order_uid);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    
	    return productOrder;
	}

//	@Override
//	public boolean update(ProductOrder productOrder) {
//		int rowCount = 1;
//		String updateSql = "UPDATE product_order\r\n"
//				+ "SET order_r_name = ?, order_r_phone = ?, order_r_addr = ?, "
//				+ "order_dfee = ?, order_t = ?, order_s = ?, order_pay = ?, gm_id = ?, gm_date = ?, order_1 = ?, order_2 = ?, order_3 = ?"
//				+ "WHERE order_id = ?;";
//		
//		try (Connection conn = ds.getConnection();
//				PreparedStatement ps = conn.prepareStatement(updateSql)) {
//			
//			ps.setString(1, productOrder.getOrder_r_name());
//			ps.setString(2, productOrder.getOrder_r_phone());
//			ps.setString(3, productOrder.getOrder_r_addr());
//			ps.setInt(4, productOrder.getOrder_dfee());
//			ps.setInt(5, productOrder.getOrder_t());
//			ps.setString(6, productOrder.getOrder_s());
//			ps.setString(7, productOrder.getOrder_pay());
//			ps.setInt(8, productOrder.getGm_id());
//			ps.setTimestamp(9, productOrder.getGm_date());
//			ps.setString(10, productOrder.getOrder_1());
//			ps.setString(11, productOrder.getOrder_2());
//			ps.setString(12, productOrder.getOrder_3());
//			ps.setInt(13, productOrder.getOrder_id());
//			
//			rowCount = ps.executeUpdate(); 
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return rowCount != 0;
//			
//	}

//	@Override 
//	public List<ProductOrder> selectByUid(String order_uid) {
//		String selectByUidSql = "SELECT * FROM product_order WHERE order_uid = ?;";
//		var list = new ArrayList<ProductOrder>();
//		
//		try (Connection conn = ds.getConnection();
//				PreparedStatement ps = conn.prepareStatement(selectByUidSql)) {
//			ps.setString(1, order_uid);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				ProductOrder productOrder = new ProductOrder();
//				productOrder.setOrder_id(rs.getInt("order_id"));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

}
