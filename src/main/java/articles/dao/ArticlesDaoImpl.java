package articles.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import articles.vo.Article;

public class ArticlesDaoImpl implements ArticlesDao {
	private DataSource ds;

	private String SelectHot = "SELECT a.art_id, u.u_name, art_title, art_content, art_po_time, art_like,\r\n"
			+ "    (SELECT pic_content FROM FurrEver.articles_pics ap WHERE a.art_id = ap.pic_art_id ORDER BY pic_id LIMIT 1) AS pic_content\r\n"
			+ "FROM\r\n"
			+ "    FurrEver.articles a\r\n"
			+ "    JOIN FurrEver.USER u ON a.art_user_id = u.uid\r\n"
			+ "WHERE\r\n"
			+ "    art_status = '1'\r\n"
			+ "ORDER BY\r\n"
			+ "    art_like desc\r\n"
			+ "LIMIT 3;";
	private String SelectNew = "SELECT a.art_id, u.u_name, art_title, art_content, art_po_time, art_like,\r\n"
			+ "    (SELECT pic_content FROM FurrEver.articles_pics ap WHERE a.art_id = ap.pic_art_id ORDER BY pic_id LIMIT 1) AS pic_content\r\n"
			+ "FROM\r\n"
			+ "    FurrEver.articles a\r\n"
			+ "    JOIN FurrEver.USER u ON a.art_user_id = u.uid\r\n"
			+ "WHERE\r\n"
			+ "    art_status = '1'\r\n"
			+ "ORDER BY\r\n"
			+ "    art_po_time DESC\r\n"
			+ "LIMIT 3;";
	private String Search = "SELECT a.art_id, u.u_name, art_title, art_content, art_po_time, art_like,\r\n"
			+ "	(SELECT pic_content FROM FurrEver.articles_pics ap WHERE a.art_id = ap.pic_art_id ORDER BY pic_id LIMIT 1) AS pic_content\r\n"
			+ "FROM\r\n"
			+ "	FurrEver.articles a\r\n"
			+ "    JOIN FurrEver.USER u ON a.art_user_id = u.uid\r\n"
			+ "WHERE art_status = '1' and art_title LIKE ?\r\n"
			+ "ORDER BY art_like DESC\r\n"
			+ "LIMIT 3;";

	public ArticlesDaoImpl() {

		try {
			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/javaFramework");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Article> selectHot() {
		var list = new ArrayList<Article>();
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SelectHot);
				ResultSet rs = pstmt.executeQuery();) {

			while (rs.next()) {
				Article article = new Article();
				article.setArt_id(rs.getInt("art_id"));
				article.setU_name(rs.getString("u_name"));
				article.setArt_title(rs.getString("art_title"));
				article.setArt_content(rs.getString("art_content"));
				article.setArt_po_time(rs.getTimestamp("art_po_time"));
				article.setArt_like(rs.getInt("art_like"));
				String pic_content = new String(Base64.getEncoder().encode(rs.getBytes("pic_content")));
				article.setPic_content(pic_content);
				list.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public List<Article> selectNew() {
		var list = new ArrayList<Article>();
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SelectNew);
				ResultSet rs = pstmt.executeQuery();) {

			while (rs.next()) {

				Article article = new Article();
				article.setArt_id(rs.getInt("art_id"));
				article.setU_name(rs.getString("u_name"));
				article.setArt_title(rs.getString("art_title"));
				article.setArt_content(rs.getString("art_content"));
				article.setArt_po_time(rs.getTimestamp("art_po_time"));
				article.setArt_like(rs.getInt("art_like"));
				String pic_content = new String(Base64.getEncoder().encode(rs.getBytes("pic_content")));
				article.setPic_content(pic_content);
				list.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Article> search(String order) {
		var list = new ArrayList<Article>();
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(Search);) {
			pstmt.setString(1, "%"+order+"%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Article article = new Article();
				article.setArt_id(rs.getInt("art_id"));
				article.setU_name(rs.getString("u_name"));
				article.setArt_title(rs.getString("art_title"));
				article.setArt_content(rs.getString("art_content"));
				article.setArt_po_time(rs.getTimestamp("art_po_time"));
				article.setArt_like(rs.getInt("art_like"));
				String pic_content = new String(Base64.getEncoder().encode(rs.getBytes("pic_content")));
				article.setPic_content(pic_content);
				list.add(article);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}