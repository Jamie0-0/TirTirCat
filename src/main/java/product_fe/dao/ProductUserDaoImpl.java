package product_fe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import product_fe.vo.ProductUser;


public class ProductUserDaoImpl implements ProductUserDao {
	private DataSource ds;

	public ProductUserDaoImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FurrEver");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}



	@Override
	public ProductUser selectByUserName(String name) {
		final String sql = "select * from USER where u_name = ?";
		try (Connection connection = ds.getConnection(); PreparedStatement pstm = connection.prepareStatement(sql);) {
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				ProductUser productUser = new ProductUser();
				productUser.setUid(rs.getInt("uid"));
				productUser.setName(rs.getString("u_name"));
				return productUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProductUser selectByUserNameForCart(String name) {
		final String sql = "select * from USER where u_name = ?";
		try (Connection connection = ds.getConnection(); PreparedStatement pstm = connection.prepareStatement(sql);) {
			pstm.setString(1, name);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				ProductUser productUser = new ProductUser();
				productUser.setUid(rs.getInt("uid"));
				productUser.setName(rs.getString("u_name"));
				productUser.setPhone(rs.getString("u_phone"));
				productUser.setAddr(rs.getString("u_address"));
				return productUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public ProductUser selectByUidForCart(int uid) {
		final String sql = "select uid, u_name, u_phone, u_address from USER where uid = ?;";
		try (Connection connection = ds.getConnection(); PreparedStatement pstm = connection.prepareStatement(sql);) {
			pstm.setInt(1, uid);
			ResultSet rs = pstm.executeQuery();
			if (rs.next()) {
				ProductUser productUser = new ProductUser();
				productUser.setUid(rs.getInt("uid"));
				productUser.setName(rs.getString("u_name"));
				productUser.setPhone(rs.getString("u_phone"));
				productUser.setAddr(rs.getString("u_address"));
				return productUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
