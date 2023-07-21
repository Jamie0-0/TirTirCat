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

	private static final String LOGIN = "select u_name FROM USER WHERE u_email=? && u_pwd=?";
	private static final String REGISTER = "insert into USER(u_email, u_name, u_pwd, u_phone, u_address, u_birth, u_gender) values(?, ?, ?, ?, ?, ?, ?)";

	public MemberDaoImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/javaFramework");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}


	public String login(String email, String password) {
		String username = null;
		try (Connection connection = ds.getConnection(); PreparedStatement pstm = connection.prepareStatement(LOGIN);) {

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
	public boolean insert(Member member) {
		try (Connection connection = ds.getConnection();
				PreparedStatement pstm = connection.prepareStatement(REGISTER);) {
			pstm.setString(1, member.getEmail());
			pstm.setString(2, member.getName());
			pstm.setString(3, member.getPassword());
			pstm.setString(4, member.getPhone());
			pstm.setString(5, member.getAddr());
			pstm.setDate(6, member.getBirth());
			pstm.setString(7, member.getGender());

			int rowInsert = pstm.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
