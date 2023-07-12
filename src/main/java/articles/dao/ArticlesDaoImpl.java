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
import articles.vo.ArticlePic;

public class ArticlesDaoImpl implements ArticlesDao {
	private DataSource ds;

	private String selectHot = "SELECT a.art_id, u.u_name, art_title, art_content, art_po_time, art_like\r\n"
			+ "FROM\r\n" + "    FurrEver.articles a\r\n" + "    JOIN FurrEver.USER u ON a.art_user_id = u.uid\r\n"
			+ "WHERE\r\n" + "    art_status = '1'\r\n" + "ORDER BY\r\n" + "    art_like desc\r\n" + "LIMIT 3;";
	private String selectNew = "SELECT a.art_id, u.u_name, art_title, art_content, art_po_time, art_like\r\n"
			+ "FROM\r\n" + "    FurrEver.articles a\r\n" + "    JOIN FurrEver.USER u ON a.art_user_id = u.uid\r\n"
			+ "WHERE\r\n" + "    art_status = '1'\r\n" + "ORDER BY\r\n" + "    art_po_time DESC\r\n" + "LIMIT 3;";
	private String search = "SELECT a.art_id, u.u_name, art_title, art_content, art_po_time, art_like\r\n" + "FROM\r\n"
			+ "	FurrEver.articles a\r\n" + "    JOIN FurrEver.USER u ON a.art_user_id = u.uid\r\n"
			+ "WHERE art_status = '1' and art_title LIKE ?\r\n" + "ORDER BY art_like DESC\r\n" + "LIMIT 3;";
	private String selectPic = "SELECT ap.pic_art_id, MIN(ap.pic_content) AS pic_content\r\n"
			+ "FROM FurrEver.articles_pics ap\r\n" + "where ap.pic_art_id = ?";

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
				PreparedStatement pstmt = conn.prepareStatement(selectHot);
				ResultSet rs = pstmt.executeQuery();) {

			while (rs.next()) {
				Article article = new Article();
				article.setArt_id(rs.getInt("art_id"));
				article.setU_name(rs.getString("u_name"));
				article.setArt_title(rs.getString("art_title"));
				article.setArt_content(rs.getString("art_content"));
				article.setArt_po_time(rs.getTimestamp("art_po_time"));
				article.setArt_like(rs.getInt("art_like"));
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
				PreparedStatement pstmt = conn.prepareStatement(selectNew);
				ResultSet rs = pstmt.executeQuery();) {

			while (rs.next()) {

				Article article = new Article();
				article.setArt_id(rs.getInt("art_id"));
				article.setU_name(rs.getString("u_name"));
				article.setArt_title(rs.getString("art_title"));
				article.setArt_content(rs.getString("art_content"));
				article.setArt_po_time(rs.getTimestamp("art_po_time"));
				article.setArt_like(rs.getInt("art_like"));
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
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(search);) {
			pstmt.setString(1, "%" + order + "%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				Article article = new Article();
				article.setArt_id(rs.getInt("art_id"));
				article.setU_name(rs.getString("u_name"));
				article.setArt_title(rs.getString("art_title"));
				article.setArt_content(rs.getString("art_content"));
				article.setArt_po_time(rs.getTimestamp("art_po_time"));
				article.setArt_like(rs.getInt("art_like"));
				list.add(article);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArticlePic selectPic(String art_id) {
		ArticlePic articlePic = null;
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectPic);) {
			pstmt.setString(1, art_id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				articlePic = new ArticlePic();
				String pic_content = new String(Base64.getEncoder().encode(rs.getBytes("pic_content")));
				articlePic.setPic_content(pic_content);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articlePic;
	}

}