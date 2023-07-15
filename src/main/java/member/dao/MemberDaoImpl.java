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

	private String login = "select u_pwd FROM USER WHERE u_email=?";

	public MemberDaoImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/javaFramework");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Member> login(){
		try(
				Connection connection = ds.getConnection();
				PreparedStatement pstm = connection.prepareStatement(login);
				ResultSet rs = pstm.executeQuery();
				) {
			var list = new ArrayList<Member>();
			while (rs.next()) {
				Member member = new Member();
				member.setuEmail(rs.getString("u_email"));
				member.setuPwd(rs.getString("u_pwd"));
				list.add(member);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
