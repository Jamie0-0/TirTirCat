package gb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import gb.utils.GBDatabaseUtil;
import gb.vo.GbAndProductVO;
import gb.vo.GbVO;
import gb.vo.ProductVO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class GbDAOImpl implements GBDao {
	private static final String INSERT_GB_SQL = "INSERT INTO GB (gb_p_id, gb_s_price, gb_c_max, gb_time_start, gb_time_end, gb_status) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_GB_SQL = "UPDATE GB SET gb_p_id = ?, gb_s_price = ?, gb_c_max = ?, gb_time_start = ?, gb_time_end = ?, gb_status = ? WHERE gb_id = ?";
	private static final String DELETE_GB_SQL = "DELETE FROM GB WHERE gb_id = ?";
	private static final String SELECT_GB_BY_ID_SQL = "SELECT * FROM GB WHERE gb_id = ?";

	private static final String SELECT_ALL_GB_SQL = "SELECT * FROM GB";
	private static final String JOIN_PRODUCT_SQL = "SELECT gb.gb_id, gb.gb_p_id, gb.gb_s_price, gb.gb_c_max, gb.gb_time_start, gb.gb_time_end, gb.gb_satus, "
			+ "p.p_id, p.p_m_id, p.p_name, p.p_price, p.p_stock, p.p_count, p.p_type, p.p_class, p.p_upload_time, "
			+ "p.p_des, p.p_status, p.p_pic_one, p.p_pic_two, p.p_pic_three, p.p_pic_four, p.p_1, p.p_2, p.p_3 "
			+ "FROM gb gb JOIN product p ON gb.gb_p_id = p.p_id";

	private static final String INSERT_PRODUCT_SQL = "INSERT INTO product (p_m_id, p_name, p_price, p_stock, p_count, p_type, p_class, p_upload_time, p_des, p_status, p_pic_one, p_pic_two, p_pic_three, p_pic_four, p_1, p_2, p_3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//	private static final String CHOICE_PRODUCT_SQL = "SELECT gb.gb_id, gb.gb_p_id, gb.gb_s_price, gb.gb_c_max, gb.gb_time_start, gb.gb_time_end, gb.gb_satus, "
//		    + "p.p_id, p.p_m_id, p.p_name, p.p_price, p.p_stock, p.p_count, p.p_type, p.p_class, p.p_upload_time, "
//		    + "p.p_des, p.p_status, p.p_pic_one, p.p_pic_two, p.p_pic_three, p.p_pic_four, p.p_1, p.p_2, p.p_3 "
//		    + "FROM gb gb JOIN product p ON gb.gb_p_id = p.p_id "
//		    + "WHERE ? = ?";

	private DataSource ds;

	public GbDAOImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FurrEver");
		} catch (Exception e) {
		}
	}

	@Override
	public void insert(GbVO gbVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = GBDatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(INSERT_GB_SQL);
			pstmt.setInt(1, gbVO.getGb_p_id());
			pstmt.setInt(2, gbVO.getGb_s_price());
			pstmt.setInt(3, gbVO.getGb_c_max());
//			pstmt.setDate(4, new java.sql.Date(gbVO.getGb_time_start().getTime()));
//			pstmt.setDate(5, new java.sql.Date(gbVO.getGb_time_end().getTime()));
			pstmt.setTimestamp(4, new Timestamp(gbVO.getGb_time_start().getTime()));
			pstmt.setTimestamp(5, new Timestamp(gbVO.getGb_time_end().getTime()));

			pstmt.setInt(6, gbVO.getGb_status());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			GBDatabaseUtil.closeResources(conn, pstmt);
		}
	}

	@Override
	public void update(GbVO gbVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = GBDatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(UPDATE_GB_SQL);
			pstmt.setInt(1, gbVO.getGb_p_id());
			pstmt.setInt(2, gbVO.getGb_s_price());
			pstmt.setInt(3, gbVO.getGb_c_max());
//			pstmt.setDate(4, new java.sql.Date(gbVO.getGb_time_start().getTime()));
//			pstmt.setDate(5, new java.sql.Date(gbVO.getGb_time_end().getTime()));
			pstmt.setTimestamp(4, new Timestamp(gbVO.getGb_time_start().getTime()));
			pstmt.setTimestamp(5, new Timestamp(gbVO.getGb_time_end().getTime()));

			pstmt.setInt(6, gbVO.getGb_status());
			pstmt.setInt(7, gbVO.getGb_id());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			GBDatabaseUtil.closeResources(conn, pstmt);
		}
	}

	@Override
	public void delete(Integer gb_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = GBDatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(DELETE_GB_SQL);
			pstmt.setInt(1, gb_id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			GBDatabaseUtil.closeResources(conn, pstmt);
		}
	}

	@Override
	public GbVO findByPrimaryKey(Integer gb_id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GbVO gbVO = null;

		try {
			conn = GBDatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SELECT_GB_BY_ID_SQL);
			pstmt.setInt(1, gb_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				gbVO = new GbVO();
				gbVO.setGb_id(rs.getInt("gb_id"));
				gbVO.setGb_p_id(rs.getInt("gb_p_id"));
				gbVO.setGb_s_price(rs.getInt("gb_s_price"));
				gbVO.setGb_c_max(rs.getInt("gb_c_max"));
//				gbVO.setGb_time_start(rs.getDate("gb_time_start"));
//				gbVO.setGb_time_end(rs.getDate("gb_time_end"));
				gbVO.setGb_time_start(rs.getTimestamp("gb_time_start"));
				gbVO.setGb_time_end(rs.getTimestamp("gb_time_end"));

				gbVO.setGb_status(rs.getInt("gb_status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			GBDatabaseUtil.closeResources(conn, pstmt, rs);
		}

		return gbVO;
	}

	@Override
	public List<GbVO> getAll() {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GbVO> gbList = new ArrayList<>();

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_GB_SQL);) {
//			conn = GBDatabaseUtil.getConnection();
//			pstmt = conn.prepareStatement(SELECT_ALL_GB_SQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				GbVO gbVO = new GbVO();
				gbVO.setGb_id(rs.getInt("gb_id"));
				gbVO.setGb_p_id(rs.getInt("gb_p_id"));
				gbVO.setGb_s_price(rs.getInt("gb_s_price"));
				gbVO.setGb_c_max(rs.getInt("gb_c_max"));
//				gbVO.setGb_time_start(rs.getDate("gb_time_start"));
//				gbVO.setGb_time_end(rs.getDate("gb_time_end"));
				gbVO.setGb_time_start(rs.getTimestamp("gb_time_start"));
				gbVO.setGb_time_end(rs.getTimestamp("gb_time_end"));

				gbVO.setGb_status(rs.getInt("gb_satus"));
				gbList.add(gbVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return gbList;
	}

	// join product table
	@Override
	public List<GbAndProductVO> getGbAndProductJoin() {
		List<GbAndProductVO> result = new ArrayList<>();

		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(JOIN_PRODUCT_SQL);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				GbVO gbVO = new GbVO();
				ProductVO productVO = new ProductVO();

				gbVO.setGb_id(rs.getInt("gb_id"));
				gbVO.setGb_p_id(rs.getInt("gb_p_id"));
				gbVO.setGb_s_price(rs.getInt("gb_s_price"));
				gbVO.setGb_c_max(rs.getInt("gb_c_max"));
				gbVO.setGb_time_start(rs.getTimestamp("gb_time_start"));
				gbVO.setGb_time_end(rs.getTimestamp("gb_time_end"));
				gbVO.setGb_status(rs.getInt("gb_satus"));

				productVO.setP_id(rs.getInt("p_id"));
				productVO.setP_m_id(rs.getInt("p_m_id"));
				productVO.setP_name(rs.getString("p_name"));
				productVO.setP_price(rs.getInt("p_price"));
				productVO.setP_stock(rs.getInt("p_stock"));
				productVO.setP_count(rs.getInt("p_count"));
				productVO.setP_type(rs.getInt("p_type"));
				productVO.setP_class(rs.getInt("p_class"));
				productVO.setP_upload_time(rs.getDate("p_upload_time"));
				productVO.setP_des(rs.getString("p_des"));
				productVO.setP_status(rs.getInt("p_status"));
				productVO.setP_pic_one(rs.getBytes("p_pic_one"));
				productVO.setP_pic_two(rs.getBytes("p_pic_two"));
				productVO.setP_pic_three(rs.getBytes("p_pic_three"));
				productVO.setP_pic_four(rs.getBytes("p_pic_four"));

				GbAndProductVO gbAndProductVO = new GbAndProductVO();
				gbAndProductVO.setGbVO(gbVO);
				gbAndProductVO.setProductVO(productVO);
//				System.out.println("123");
				result.add(gbAndProductVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

//以下/////////////////////////////////////////////////////////////////////////
	@Override
	public void insertProductWithImages(ProductVO product) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = GBDatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(INSERT_PRODUCT_SQL);

			// 設定圖片的值 // 省略 設定其他商品資料的值s
			String imageFolderPath = "/images/";
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			GBDatabaseUtil.closeResources(conn, pstmt);
		}
	}

	private byte[] convertImageToBase64(String base64Data) {
		return Base64.getDecoder().decode(base64Data);
	}

	@Override
	public List<GbAndProductVO> selectByKeyWords(String how,String keywords) {
		String columnName = how;
		String query = "SELECT gb.gb_id, gb.gb_p_id, gb.gb_s_price, gb.gb_c_max, gb.gb_time_start, gb.gb_time_end, gb.gb_satus, "
		        + "p.p_id, p.p_m_id, p.p_name, p.p_price, p.p_stock, p.p_count, p.p_type, p.p_class, p.p_upload_time, "
		        + "p.p_des, p.p_status, p.p_pic_one, p.p_pic_two, p.p_pic_three, p.p_pic_four, p.p_1, p.p_2, p.p_3 "
		        + "FROM gb gb JOIN product p ON gb.gb_p_id = p.p_id "
		        + "WHERE " + columnName + " = ?";
		List<GbAndProductVO> result = new ArrayList<>();

		try (Connection conn = ds.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query);
				) {
			
//			stmt.setString(1, "'how'");
			stmt.setString(1, keywords);
			
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
				GbVO gbVO = new GbVO();
				ProductVO productVO = new ProductVO();

				gbVO.setGb_id(rs.getInt("gb_id"));
				gbVO.setGb_p_id(rs.getInt("gb_p_id"));
				gbVO.setGb_s_price(rs.getInt("gb_s_price"));
				gbVO.setGb_c_max(rs.getInt("gb_c_max"));
				gbVO.setGb_time_start(rs.getTimestamp("gb_time_start"));
				gbVO.setGb_time_end(rs.getTimestamp("gb_time_end"));
				gbVO.setGb_status(rs.getInt("gb_satus"));

				productVO.setP_id(rs.getInt("p_id"));
				productVO.setP_m_id(rs.getInt("p_m_id"));
				productVO.setP_name(rs.getString("p_name"));
				productVO.setP_price(rs.getInt("p_price"));
				productVO.setP_stock(rs.getInt("p_stock"));
				productVO.setP_count(rs.getInt("p_count"));
				productVO.setP_type(rs.getInt("p_type"));
				productVO.setP_class(rs.getInt("p_class"));
				productVO.setP_upload_time(rs.getDate("p_upload_time"));
				productVO.setP_des(rs.getString("p_des"));
				productVO.setP_status(rs.getInt("p_status"));
				productVO.setP_pic_one(rs.getBytes("p_pic_one"));
				productVO.setP_pic_two(rs.getBytes("p_pic_two"));
				productVO.setP_pic_three(rs.getBytes("p_pic_three"));
				productVO.setP_pic_four(rs.getBytes("p_pic_four"));

				GbAndProductVO gbAndProductVO = new GbAndProductVO();
				gbAndProductVO.setGbVO(gbVO);
				gbAndProductVO.setProductVO(productVO);
				result.add(gbAndProductVO);
			}
			return result; 
		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;
	}

//以上。//////////////////////////////////////////////////////////////

}
