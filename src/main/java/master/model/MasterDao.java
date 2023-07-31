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
	
	// 一個應用程式中,針對一個資料庫 ,共用一個DataSource即可
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
	private String UPDATE = "UPDATE product set m_name=?,m_pwd=?,m_gui=?"
							+ ",m_bank_name=?,m_bank_id=?,m_address=?,m_man_id=?"
							+ ", m_man_name=?, m_email=?, m_phone=? "
							+ " where m_id=?";

	@Override
	public void update(MasterVO masterVO) {
		//  private String m_name; 廠商名稱
		//  private String m_pwd;  密碼
		//  private String m_gui;  統編
//			private String m_bank_name; 銀行行號
//			private String m_bank_id;   銀行帳號
//			private String m_address;   廠商地址
//			private String m_man_id;    身分證
//			private String m_man_name;  聯絡人名稱
//			private String m_email;     信箱
//			private String m_phone;     電話
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			// 這裡是寫每個欄位的輸入
			pstmt.setString(1, masterVO.getM_name());
			pstmt.setString(2, masterVO.getM_pwd());
			pstmt.setString(3, masterVO.getM_gui());
			pstmt.setString(4, masterVO.getM_bank_name());
			pstmt.setString(5, masterVO.getM_bank_id());
			pstmt.setString(6, masterVO.getM_address());
			pstmt.setString(7, masterVO.getM_man_id());
			pstmt.setString(8, masterVO.getM_man_name());
			pstmt.setString(9, masterVO.getM_email());
			pstmt.setString(10, masterVO.getM_phone());

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
										.setM_pwd(rs.getString("m_pwd"))
										.setM_gui(rs.getString("m_gui"))
										.setM_bank_name(rs.getString("m_bank_name"))
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