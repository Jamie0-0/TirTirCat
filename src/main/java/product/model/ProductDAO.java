package product.model;

import java.util.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDAO implements ProductDAO_interface  {
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			// 這裡是刪除功能，因為getM_id是key值，只要輸入下面一句話，就完成這一整段
			pstmt.setInt(1, getP_id);

			pstmt.executeUpdate();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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
}
