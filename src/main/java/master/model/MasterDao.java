package master.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MasterDao implements MasterDao_interface{
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/tha102");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String GET_ONE_STMT = "SELECT * FROM master where m_id = ?";
	private String UPDATE = "UPDATE master set m_name=?,m_gui=?"
						+ ",m_bank_id=?,m_address=?,m_man_id=?"
						+ ", m_man_name=?, m_email=?, m_phone=? "
						+ " where m_id=?";

	@Override
	public void update(MasterVO masterVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			// 這裡是寫每個欄位的輸入
			pstmt.setString(1, masterVO.getM_name());
			pstmt.setString(2, masterVO.getM_gui());
			pstmt.setString(3, masterVO.getM_bank_id());
			pstmt.setString(4, masterVO.getM_address());
			pstmt.setString(5, masterVO.getM_man_id());
			pstmt.setString(6, masterVO.getM_man_name());
			pstmt.setString(7, masterVO.getM_email());
			pstmt.setString(8, masterVO.getM_phone());
			pstmt.setInt(9, masterVO.getM_id());

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
	public MasterVO findByPrimaryKey(Integer m_id) {
		MasterVO masterVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, m_id);
			rs = pstmt.executeQuery();

			// 這裡是查到的值，須將所有欄位值放進去
			while (rs.next()) {
				masterVO = new MasterVO.Builder()
										.setM_id(rs.getInt("m_id"))
										.setM_name(rs.getString("m_name"))
										.setM_gui(rs.getString("m_gui"))
										.setM_bank_id(rs.getString("m_bank_id"))
										.setM_address(rs.getString("m_address"))
										.setM_man_id(rs.getString("m_man_id"))
										.setM_man_name(rs.getString("m_man_name"))
										.setM_email(rs.getString("m_email"))
										.setM_phone(rs.getString("m_phone"))
										.build();
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
		return masterVO;
	}
}
