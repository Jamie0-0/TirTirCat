package product_fe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import product_fe.vo.Product;

public class ProductDaoImpl implements ProductDao {
	private DataSource ds;

	public ProductDaoImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FurrEver");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> selectAll() {
		String selectAllSql = "select * from product;";
		var list = new ArrayList<Product>();

		try (Connection conn = ds.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(selectAllSql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setP_id(rs.getInt("p_id"));
				product.setP_m_id(rs.getInt("p_m_id"));
				product.setP_name(rs.getString("p_name"));
				product.setP_price(rs.getInt("p_price"));
				product.setP_stock(rs.getInt("p_stock"));
				product.setP_count(rs.getInt("p_count"));
				product.setP_type(rs.getString("p_type"));
				product.setP_class(rs.getString("p_class"));
				product.setP_upload_time(rs.getTimestamp("p_upload_time"));
				product.setP_des(rs.getString("p_des"));
				product.setP_status(rs.getString("p_status"));
				product.setP_pic_one(rs.getBytes("p_pic_one"));
				product.setP_pic_two(rs.getBytes("p_pic_two"));
				product.setP_pic_three(rs.getBytes("p_pic_three"));
				product.setP_pic_four(rs.getBytes("p_pic_four"));
				product.setP_1(rs.getString("p_1"));
				product.setP_2(rs.getString("p_2"));
				product.setP_3(rs.getString("p_3"));

				list.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<Product> selectForShop(){
		String selectAllSql = "SELECT p_id, p_name, p_price, p_type, p_class FROM product WHERE p_status = 0;";
		var list = new ArrayList<Product>();

		try (Connection conn = ds.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(selectAllSql)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setP_id(rs.getInt("p_id"));
				product.setP_name(rs.getString("p_name"));
				product.setP_price(rs.getInt("p_price"));
				product.setP_type(rs.getString("p_type"));
				product.setP_class(rs.getString("p_class"));
//				product.setP_pic_one(rs.getBytes("p_pic_one"));

				list.add(product);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Product> selectByPType(String p_type) {
		String selectByPTypeSql = "SELECT p_id, p_name, p_price, p_type, p_class, p_pic_one FROM product WHERE p_status = 0 and p_type = ?;";
		var list = new ArrayList<Product>();
		
		try (Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(selectByPTypeSql)) {
			ps.setString(1, p_type);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setP_id(rs.getInt("p_id"));
				product.setP_name(rs.getString("p_name"));
				product.setP_price(rs.getInt("p_price"));
				product.setP_type(rs.getString("p_type"));
				product.setP_class(rs.getString("p_class"));
				product.setP_pic_one(rs.getBytes("p_pic_one"));
				
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Product> selectByPClass(String p_class) {
		String selectByPClassSql = "SELECT p_id, p_name, p_price, p_type, p_class, p_pic_one FROM product where p_status = 0 and p_class = ?;";
		var list = new ArrayList<Product>();
		
		try (Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(selectByPClassSql)) {
			ps.setString(1, p_class);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setP_id(rs.getInt("p_id"));
				product.setP_name(rs.getString("p_name"));
				product.setP_price(rs.getInt("p_price"));
				product.setP_type(rs.getString("p_type"));
				product.setP_class(rs.getString("p_class"));
				product.setP_pic_one(rs.getBytes("p_pic_one"));
				
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

//	@Override
//	public List<Product> selectByPTypeAndPClass(String p_type, String p_class) {
//		String selectByPTypeAndPClassSql = "SELECT p_id, p_name, p_price, p_type, p_class, p_pic_one FROM product where p_status = 0 and p_class = ? and p_type = ?;";
//		var list = new ArrayList<Product>();
//		
//		try (Connection conn = ds.getConnection();
//				PreparedStatement ps = conn.prepareStatement(selectByPTypeAndPClassSql)) {
//			ps.setString(1, p_type);
//			ps.setString(2, p_class);
//			ResultSet rs = ps.executeQuery();
//			while(rs.next()) {
//				Product product = new Product();
//				product.setP_id(rs.getInt("p_id"));
//				product.setP_name(rs.getString("p_name"));
//				product.setP_price(rs.getInt("p_price"));
//				product.setP_type(rs.getString("p_type"));
//				product.setP_class(rs.getString("p_class"));
//				product.setP_pic_one(rs.getBytes("p_pic_one"));
//				
//				list.add(product);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

	@Override
	public int selectPStockByPid(Integer p_id) {
		String selectPStockByPidSql = "SELECT p_stock FROM product where p_id = ?;";
		int pStock = 1;
		
		try (Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(selectPStockByPidSql)) {
			ps.setInt(1, p_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				pStock = rs.getInt("p_stock");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pStock;
	}
	
//	@Override
//	public Product selectByPId(Integer p_id) {
////		String selectByPIdSql = "SELECT * FROM product where p_id = ?;";
//		String selectByPIdSql = "SELECT p_id, p_name, p_price, p_stock, p_type, p_class, p_des, m_name\r\n"
//				+ "FROM PRODUCT p join MASTER m \r\n"
//				+ "ON p.p_m_id = m.m_id\r\n"
//				+ "WHERE p_id = ?;";
//		Product product = new Product();
//		Master master = new Master();
//		
//		try (Connection conn = ds.getConnection();
//				PreparedStatement ps = conn.prepareStatement(selectByPIdSql)) {
//			ps.setInt(1, p_id);
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				product.setP_id(rs.getInt("p_id"));
////				product.setP_m_id(rs.getInt("p_m_id"));
//				product.setP_name(rs.getString("p_name"));
//				product.setP_price(rs.getInt("p_price"));
//				product.setP_stock(rs.getInt("p_stock"));
////				product.setP_count(rs.getInt("p_count"));
//				product.setP_type(rs.getString("p_type"));
//				product.setP_class(rs.getString("p_class"));
////				product.setP_upload_time(rs.getTimestamp("p_upload_time"));
//				product.setP_des(rs.getString("p_des"));
//				master.setM_name(rs.getString("m_name"));
////				product.setP_status(rs.getString("p_status"));
////				product.setP_pic_one(rs.getBytes("p_pic_one"));
////				product.setP_pic_two(rs.getBytes("p_pic_two"));
////				product.setP_pic_three(rs.getBytes("p_pic_three"));
////				product.setP_pic_four(rs.getBytes("p_pic_four"));
////				product.setP_1(rs.getString("p_1"));
////				product.setP_2(rs.getString("p_2"));
////				product.setP_3(rs.getString("p_3"));
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return product;
//	}

	
	@Override
	public Product selectByPId(Integer p_id) {
//		String selectByPIdSql = "SELECT * FROM product where p_id = ?;";
		String selectByPIdSql = "SELECT p_id, p_name, p_price, p_stock, p_type, p_class, p_des, m_name\r\n"
				+ "FROM PRODUCT p join MASTER m \r\n"
				+ "ON p.p_m_id = m.m_id\r\n"
				+ "WHERE p_id = ?;";
		Product product = new Product();
		
		try (Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(selectByPIdSql)) {
			ps.setInt(1, p_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				product.setP_id(rs.getInt("p_id"));
//				product.setP_m_id(rs.getInt("p_m_id"));
				product.setP_name(rs.getString("p_name"));
				product.setP_price(rs.getInt("p_price"));
				product.setP_stock(rs.getInt("p_stock"));
//				product.setP_count(rs.getInt("p_count"));
				product.setP_type(rs.getString("p_type"));
				product.setP_class(rs.getString("p_class"));
//				product.setP_upload_time(rs.getTimestamp("p_upload_time"));
				product.setP_des(rs.getString("p_des"));
//				master.setM_name(rs.getString("m_name"));
//				product.setP_status(rs.getString("p_status"));
//				product.setP_pic_one(rs.getBytes("p_pic_one"));
//				product.setP_pic_two(rs.getBytes("p_pic_two"));
//				product.setP_pic_three(rs.getBytes("p_pic_three"));
//				product.setP_pic_four(rs.getBytes("p_pic_four"));
//				product.setP_1(rs.getString("p_1"));
//				product.setP_2(rs.getString("p_2"));
//				product.setP_3(rs.getString("p_3"));
                product.setM_name(rs.getString("m_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public List<Product> search(String inputText) {
		String searchSql = "SELECT p_id, p_name, p_price, p_type, p_class, p_pic_one FROM product where p_status = 0 and p_name like ?;";
		var list = new ArrayList<Product>();

		try (Connection conn = ds.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(searchSql)) {
			ps.setString(1, "%" + inputText + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Product product = new Product();
				product.setP_id(rs.getInt("p_id"));
				product.setP_name(rs.getString("p_name"));
				product.setP_price(rs.getInt("p_price"));
				product.setP_type(rs.getString("p_type"));
				product.setP_class(rs.getString("p_class"));
				product.setP_pic_one(rs.getBytes("p_pic_one"));
				
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public byte[] selectPicByPid(Integer p_id) {
		String selectPicByPidSql = "SELECT p_pic_one FROM product where p_id = ?;";
		byte[] pic_content = new byte[1024];
		
		try (Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(selectPicByPidSql)) {
			ps.setInt(1, p_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pic_content = rs.getBytes("p_pic_one");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pic_content;
	}

	@Override
	public int updatePCountByPid(Integer p_id, Integer quantity) {
		String updatePCountByPidSql = "UPDATE product SET p_stock = p_stock - ? WHERE p_id = ?;";
		int rowCount = 0;
		
		try (Connection conn = ds.getConnection();
				PreparedStatement ps = conn.prepareStatement(updatePCountByPidSql)) {
			ps.setInt(1, p_id);
			ps.setInt(2, quantity);
			rowCount = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return rowCount;
	}

//	@Override
//	public List<Product> selectByPIds(List<Integer> p_id) {
//		String selectByPIdsSql = "SELECT * FROM product where p_id = ?;";
//		var list = new ArrayList<Product>();
//		
//		try (Connection conn = ds.getConnection();
//				PreparedStatement ps = conn.prepareStatement(selectByPIdsSql)) {
//			
//			ps.setInt(1, p_id);
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				Product product = new Product();
//				product.setP_id(rs.getInt("p_id"));
//				product.setP_name(rs.getString("p_name"));
//				product.setP_price(rs.getInt("p_price"));
//				product.setP_pic_one(rs.getBytes("p_pic_one"));
//				
//				list.add(product);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}



}
