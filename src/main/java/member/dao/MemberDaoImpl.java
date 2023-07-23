package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import member.vo.Member;

public class MemberDaoImpl implements MemberDao {
	private DataSource ds;

	public MemberDaoImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FurrEver");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(Member member) {
		final String register = "insert into USER(u_email, u_name, u_pwd, u_phone, u_address, u_birth, u_gender, u_about) values(?, ?, ?, ?, ?, ?, ?,?)";
		try (Connection connection = ds.getConnection();
				PreparedStatement pstm = connection.prepareStatement(register);) {
			pstm.setString(1, member.getEmail());
			pstm.setString(2, member.getName());
			pstm.setString(3, member.getPassword());
			pstm.setString(4, member.getPhone());
			pstm.setString(5, member.getAddr());
			pstm.setDate(6, member.getBirth());
			pstm.setString(7, member.getGender());
			pstm.setString(8, member.getAbout());

			int rowInsert = pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Member selectByUserName(String name) {
		final String sql = "select * from USER where u_name = ?";
		try (Connection connection = ds.getConnection(); PreparedStatement pstm = connection.prepareStatement(sql);) {
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				Member member = new Member();
				member.setEmail(rs.getString("u_email"));
				member.setName(rs.getString("u_name"));
				member.setPassword(rs.getString("u_pwd"));
				member.setPhone(rs.getString("u_phone"));
				member.setAddr(rs.getString("u_address"));
				member.setBirth(rs.getDate("u_birth"));
				member.setGender(rs.getString("u_gender"));
				member.setuReg(rs.getTimestamp("u_reg"));
				member.setuPic(rs.getBytes("u_pic"));
				member.setuReport(rs.getInt("u_report"));
				member.setuStatus(rs.getInt("u_status"));
				member.setGmID(rs.getInt("gm_id"));
				member.setGmDate(rs.getTimestamp("gm_date"));
				member.setAbout(rs.getString("u_about"));
				return member;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String login(String email, String password) {
		final String login = "select u_name FROM USER WHERE u_email=? && u_pwd=?";
		String username = null;
		try (Connection connection = ds.getConnection(); PreparedStatement pstm = connection.prepareStatement(login);) {

			pstm.setString(1, email);
			pstm.setString(2, password);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				username = rs.getString("u_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return username;
	}

	@Override
	public int update(Member member) {
		final String update = "update USER set u_email = ?, u_name=?, u_pwd=?, u_phone=?, u_about=?, u_pic=? WHERE u_name=? ";
		
		try (Connection connection = ds.getConnection(); 
				PreparedStatement pstm = connection.prepareStatement(update);){
			pstm.setString(1, member.getEmail());
			pstm.setString(2, member.getName());
			pstm.setString(3, member.getPassword());
			pstm.setString(4, member.getPhone());
//			pstm.setDate(5, member.getBirth());
			pstm.setString(5, member.getAbout());
			pstm.setBytes(6, member.getuPic());
			pstm.setString(7, member.getName());
			int result = pstm.executeUpdate();
			 return result;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
