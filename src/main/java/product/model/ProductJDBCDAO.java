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

import master.model.MasterPicVO;
import master.model.MasterPicVO2;
import product.model.ProductVO;

public class ProductJDBCDAO implements ProductDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/Furrever?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

	private static final String GET_ALL_STMT = "SELECT * FROM product where p_m_id=? order by p_id";

	
    @Override
    public ProductVO indexValue(Integer p_m_id) {

    	return null;
    }
    
    @Override
    public List<MasterPicVO> indexNatrix1(Integer p_m_id){

    	return null;
    }

    @Override
    public List<MasterPicVO2> indexNatrix2(){
    	
    	
    	
    	return null;
    }

	@Override
	public List<ProductVO> searchLatest() {
		
		return null;
	}

	
	
	@Override
	public void insert(ProductVO productVO) {

	}
	
	@Override
	public void update(ProductVO productVO) {

	}
	
	@Override
	public void delete(Integer getP_id) {

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
			pstmt.setInt(1, 1);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO.Builder()
											.setP_id(rs.getInt("p_id"))
											.setP_m_id(rs.getInt("p_m_id"))
											.setP_name(rs.getString("p_name"))
											.setP_price(rs.getInt("p_price"))
											.setP_stock(rs.getInt("p_stock"))
											.setP_count(rs.getInt("p_count"))
											.setP_type(rs.getInt("p_type"))
											.setP_class(rs.getInt("p_class"))
											.setP_upload_time(rs.getTimestamp("p_upload_time").toLocalDateTime())
											.setP_des(rs.getString("p_des"))
											.setP_status(rs.getInt("p_status"))
											.setP_pic_one(rs.getBytes("p_pic_one"))
											.setP_pic_two(rs.getBytes("p_pic_two"))
											.setP_pic_three(rs.getBytes("p_pic_three"))
											.setP_pic_four(rs.getBytes("p_pic_four"))
											.setP_1(rs.getString("p_1"))
											.setP_2(rs.getString("p_2"))
											.setP_3(rs.getString("p_3"))
											.build();

//				productVO = new ProductVO();
//				productVO.setP_id(rs.getInt("p_id"));
//				productVO.setP_m_id(rs.getInt("p_m_id"));
//				productVO.setP_name(rs.getString("p_name"));
//				productVO.setP_price(rs.getInt("p_price"));
//				productVO.setP_stock(rs.getInt("p_stock"));
//				productVO.setP_count(rs.getInt("p_count"));
//				productVO.setP_type(rs.getInt("p_type"));
//				productVO.setP_class(rs.getInt("p_class"));
//				productVO.setP_upload_time(rs.getTimestamp("p_upload_time").toLocalDateTime());
//				productVO.setP_des(rs.getString("p_des"));
//				productVO.setP_status(rs.getInt("p_status"));
//				productVO.setP_pic_one(rs.getBytes("p_pic_one"));
//				productVO.setP_pic_two(rs.getBytes("p_pic_two"));
//				productVO.setP_pic_three(rs.getBytes("p_pic_three"));
//				productVO.setP_pic_four(rs.getBytes("p_pic_four"));
//				productVO.setP_1(rs.getString("p_1"));
//				productVO.setP_2(rs.getString("p_2"));
//				productVO.setP_3(rs.getString("p_3"));
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
		return null;
	}
	
	public List<ProductVO> findByPrimaryKey2(Integer p_id,Integer p_status,Integer p_class) {
		return null;
	}
	
	public static void main(String[] args) {
	
	}
}
