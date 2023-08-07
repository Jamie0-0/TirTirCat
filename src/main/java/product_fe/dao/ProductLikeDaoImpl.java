package product_fe.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import product_fe.vo.ProductLike;

public class ProductLikeDaoImpl implements ProductLikeDao {
	private DataSource ds;

	public ProductLikeDaoImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FurrEver");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int insert(int pl_uid, int pl_p_id) {
		String insertSql = "insert into product_like(pl_uid, pl_p_id) values(?, ?);";
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(insertSql)) {
			ps.setInt(1, pl_uid);
			ps.setInt(2, pl_p_id);

			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	};

	@Override
	public int delete(int pl_uid, int pl_p_id) {
		String deleteSql = "delete from product_like where pl_uid = ? and pl_p_id = ?;";
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(deleteSql)) {
			ps.setInt(1, pl_uid);
			ps.setInt(2, pl_p_id);

			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

//	@Override
//	public boolean selectByUidAndPId(String pl_uid, String pl_p_id) {
//			String selectSql = "delete from product_like where pl_uid = ? and pl_p_id = ?;";
//
//			try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(selectSql)) {
//				ps.setInt(1, Integer.parseInt(pl_uid));
//				ps.setInt(2, Integer.parseInt(pl_p_id));
//
//				ResultSet rs = ps.executeQuery();
//				while (rs.next()) {
//					ProductLike productLike = new ProductLike();
//					productLike.setPl_p_id(rs.getInt("pl_uid"));
//					productLike.setPl_uid(rs.getInt("pl_p_id"));
//					if (productLike != null) {
//						return true;
//					}
//
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//
//		return false;
//	}

//	@Override
//	public int insert(ProductLike productLike) {
//		String insertSql = "insert into product_like(pl_uid, pl_p_id) values(?, ?);";
//		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(insertSql)) {
//			ps.setInt(1, productLike.getPl_uid());
//			ps.setInt(2, productLike.getPl_p_id());
//
//			return ps.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return -1;
//		}
//	};
//
//	@Override
//	public int delete(ProductLike productLike) {
//		String deleteSql = "delete from product_like where pl_uid = ? and pl_p_id = ?;";
//		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(deleteSql)) {
//			ps.setInt(1, productLike.getPl_uid());
//			ps.setInt(2, productLike.getPl_p_id());
//
//			return ps.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return -1;
//		}
//	}

	@Override
	public ProductLike selectByUidAndPId(int pl_uid, int pl_p_id) {
		String selectSql = "select * from product_like where pl_uid = ? and pl_p_id = ?;";

		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(selectSql)) {
			ps.setInt(1, pl_uid);
			ps.setInt(2, pl_p_id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductLike productLike = new ProductLike();
				productLike.setPl_p_id(rs.getInt("pl_uid"));
				productLike.setPl_uid(rs.getInt("pl_p_id"));
				return productLike;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	};

}
