package product.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import product.model.ProductVO;

public class ProductJDBCDAO implements ProductDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Furrever?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "Aa123456";

	private static final String INSERT_STMT = "INSERT INTO product (p_m_id,p_name,p_price,p_stock,p_count,p_type,p_class,p_upload_time,p_des,p_status,p_pic_one,p_pic_two,p_pic_three,p_pic_four,p_1,p_2,p_3) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String GET_ONE_STMT = "SELECT * FROM product where p_id = ?";
	private static final String DELETE = "DELETE FROM product where p_id = ?";
	private static final String UPDATE = "UPDATE product set p_m_id=?,p_name=?,p_price=?,p_stock=?,p_count=?,p_type=?,p_class=?,p_upload_time=?,p_des=?,p_status=?,p_pic_one=?,p_pic_two=?,p_pic_three=?,p_pic_four=?,p_1=?,p_2=?,p_3=? where p_id = ?";
	private static final String GET_ALL_STMT = "SELECT * FROM product order by p_id";

	@Override
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			// 這裡是寫每個欄位的輸入
			pstmt.setInt(1, productVO.getP_m_id());
			pstmt.setString(2, productVO.getP_name());
			pstmt.setInt(3, productVO.getP_price());
			pstmt.setInt(4, productVO.getP_stock());
			pstmt.setInt(5, productVO.getP_count());
			pstmt.setInt(6, productVO.getP_type());
			pstmt.setInt(7, productVO.getP_class());
			pstmt.setObject(8, productVO.getP_upload_time());
			pstmt.setString(9, productVO.getP_des());
			pstmt.setInt(10, productVO.getP_status());
			pstmt.setBytes(11, productVO.getP_pic_one());
			pstmt.setBytes(12, productVO.getP_pic_two());
			pstmt.setBytes(13, productVO.getP_pic_three());
			pstmt.setBytes(14, productVO.getP_pic_four());
			pstmt.setString(15, productVO.getP_1());
			pstmt.setString(16, productVO.getP_2());
			pstmt.setString(17, productVO.getP_3());
			

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	@Override
	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			// 這裡是寫每個欄位的輸入
			pstmt.setInt(1, productVO.getP_m_id());
			pstmt.setString(2, productVO.getP_name());
			pstmt.setInt(3, productVO.getP_price());
			pstmt.setInt(4, productVO.getP_stock());
			pstmt.setInt(5, productVO.getP_count());
			pstmt.setInt(6, productVO.getP_type());
			pstmt.setInt(7, productVO.getP_class());
			pstmt.setObject(8, productVO.getP_upload_time());
			pstmt.setString(9, productVO.getP_des());
			pstmt.setInt(10, productVO.getP_status());
			pstmt.setBytes(11, productVO.getP_pic_one());
			pstmt.setBytes(12, productVO.getP_pic_two());
			pstmt.setBytes(13, productVO.getP_pic_three());
			pstmt.setBytes(14, productVO.getP_pic_four());
			pstmt.setString(15, productVO.getP_1());
			pstmt.setString(16, productVO.getP_2());
			pstmt.setString(17, productVO.getP_3());
			pstmt.setInt(18, productVO.getP_id());
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}
	
	@Override
	public void delete(Integer getP_id) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			// 這裡是刪除功能，因為getM_id是key值，只要輸入下面一句話，就完成這一整段
			pstmt.setInt(1, getP_id);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setP_id(rs.getInt("p_id"));
				productVO.setP_m_id(rs.getInt("p_m_id"));
				productVO.setP_name(rs.getString("p_name"));
				productVO.setP_price(rs.getInt("p_price"));
				productVO.setP_stock(rs.getInt("p_stock"));
				productVO.setP_count(rs.getInt("p_count"));
				productVO.setP_type(rs.getInt("p_type"));
				productVO.setP_class(rs.getInt("p_class"));
				productVO.setP_upload_time(rs.getTimestamp("p_upload_time").toLocalDateTime());
				productVO.setP_des(rs.getString("p_des"));
				productVO.setP_status(rs.getInt("p_status"));
				productVO.setP_pic_one(rs.getBytes("p_pic_one"));
				productVO.setP_pic_two(rs.getBytes("p_pic_two"));
				productVO.setP_pic_three(rs.getBytes("p_pic_three"));
				productVO.setP_pic_four(rs.getBytes("p_pic_four"));
				productVO.setP_1(rs.getString("p_1"));
				productVO.setP_2(rs.getString("p_2"));
				productVO.setP_3(rs.getString("p_3"));
				list.add(productVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public ProductVO findByPrimaryKey(Integer p_id) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, p_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				// 這裡是查到的值，須將所有欄位值放進去
				productVO = new ProductVO();
				productVO.setP_id(rs.getInt("p_id"));
				productVO.setP_m_id(rs.getInt("p_m_id"));
				productVO.setP_name(rs.getString("p_name"));
				productVO.setP_price(rs.getInt("p_price"));
				productVO.setP_stock(rs.getInt("p_stock"));
				productVO.setP_count(rs.getInt("p_count"));
				productVO.setP_type(rs.getInt("p_type"));
				productVO.setP_class(rs.getInt("p_class"));
				productVO.setP_upload_time(rs.getTimestamp("p_upload_time").toLocalDateTime());
				productVO.setP_des(rs.getString("p_des"));
				productVO.setP_status(rs.getInt("p_status"));
				productVO.setP_pic_one(rs.getBytes("p_pic_one"));
				productVO.setP_pic_two(rs.getBytes("p_pic_two"));
				productVO.setP_pic_three(rs.getBytes("p_pic_three"));
				productVO.setP_pic_four(rs.getBytes("p_pic_four"));
				productVO.setP_1(rs.getString("p_1"));
				productVO.setP_2(rs.getString("p_2"));
				productVO.setP_3(rs.getString("p_3"));

			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return productVO;
	}
	
	public static void main(String[] args) {

		ProductJDBCDAO dao = new ProductJDBCDAO();

//		// 新增
//		ProductVO productVO = new ProductVO();
//		productVO.setP_m_id(2);
//		productVO.setP_name("123");
//		productVO.setP_price(2);
//		productVO.setP_stock(2);
//		productVO.setP_count(2);
//		productVO.setP_type(2);
//		productVO.setP_class(2);
//		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		LocalDateTime parsedDateTime2 = LocalDateTime.parse("2023-05-26 13:42:32", formatter2);
//		productVO.setP_upload_time(parsedDateTime2);
//		productVO.setP_des("123");		
//		productVO.setP_status(2);
//		productVO.setP_pic_one(null);
//		productVO.setP_pic_two(null);
//		productVO.setP_pic_three(null);
//		productVO.setP_pic_four(null);
//		productVO.setP_1("");
//		productVO.setP_2("");
//		productVO.setP_3("");
//		dao.insert(productVO);

//		// 修改
//		ProductVO productVO = new ProductVO();
//		productVO.setP_m_id(2);
//		productVO.setP_name("123");
//		productVO.setP_price(2);
//		productVO.setP_stock(2);
//		productVO.setP_count(2);
//		productVO.setP_type(2);
//		productVO.setP_class(2);
//		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		LocalDateTime parsedDateTime2 = LocalDateTime.parse("2023-05-26 13:42:32", formatter2);
//		productVO.setP_upload_time(parsedDateTime2);
//		productVO.setP_des("123");		
//		productVO.setP_status(2);
//		productVO.setP_pic_one(null);
//		productVO.setP_pic_two(null);
//		productVO.setP_pic_three(null);
//		productVO.setP_pic_four(null);
//		productVO.setP_1("");
//		productVO.setP_2("");
//		productVO.setP_3("");
		
//		// 刪除
//		dao.delete(1);
//
//		
//		
//		
//		// 查詢
//		ProductVO productVO = dao.findByPrimaryKey(2);
//		System.out.print(productVO.getP_id() + ",");
//		System.out.print(productVO.getP_m_id() + ",");
//		System.out.print(productVO.getP_name() + ",");
//		System.out.print(productVO.getP_price() + ",");
//		System.out.print(productVO.getP_stock() + ",");
//		System.out.print(productVO.getP_count() + ",");
//		System.out.print(productVO.getP_type() + ",");
//		System.out.print(productVO.getP_class() + ",");
//		System.out.print(productVO.getP_upload_time() + ",");
//		System.out.print(productVO.getP_des() + ",");
//		System.out.print(productVO.getP_status() + ",");
//		System.out.print(productVO.getP_pic_one() + ",");
//		System.out.print(productVO.getP_pic_two() + ",");
//		System.out.print(productVO.getP_pic_three() + ",");
//		System.out.print(productVO.getP_pic_four() + ",");
//		System.out.print(productVO.getP_1() + ",");
//		System.out.print(productVO.getP_2() + ",");
//		System.out.print(productVO.getP_3() + ",");
		
		// 全部查詢
		List<ProductVO> lists = dao.getAll();
		for (ProductVO list : lists) {
			System.out.print(list.getP_id() + ",");
			System.out.print(list.getP_m_id() + ",");
			System.out.print(list.getP_name() + ",");
			System.out.print(list.getP_price() + ",");
			System.out.print(list.getP_stock() + ",");
			System.out.print(list.getP_count() + ",");
			System.out.print(list.getP_type() + ",");
			System.out.print(list.getP_class() + ",");
			System.out.print(list.getP_upload_time() + ",");
			System.out.print(list.getP_des() + ",");
			System.out.print(list.getP_status() + ",");
			System.out.print(list.getP_pic_one() + ",");
			System.out.print(list.getP_pic_two() + ",");
			System.out.print(list.getP_pic_three() + ",");
			System.out.print(list.getP_pic_four() + ",");
			System.out.print(list.getP_1() + ",");
			System.out.print(list.getP_2() + ",");
			System.out.print(list.getP_3() + ",");
			System.out.println();
		}
	}
}
