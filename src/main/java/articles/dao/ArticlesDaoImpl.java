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

	public ArticlesDaoImpl() {

		try {
			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FurrEver");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Article> selectHot(String page) {
		String selectHot = "SELECT a.art_id, u.uid, u.u_name, art_title, art_content, art_po_time, art_like\r\n"
				+ "FROM\r\n" + "    FurrEver.articles a\r\n" + "    JOIN FurrEver.USER u ON a.art_user_id = u.uid\r\n"
				+ "WHERE\r\n" + "    art_status = '1'\r\n" + "ORDER BY\r\n" + "    art_like desc\r\n" + "LIMIT ?";
		
		var list = new ArrayList<Article>();
		int pageNum = Integer.parseInt(page);
		int limit = 3;  // 跳過幾筆文章  
		if(pageNum>1) {
			selectHot = selectHot+",3";  // 3 為頁面上顯示的文章數量
			limit = ((pageNum-1)*3); //第二頁跳過3筆
		}
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(selectHot);
				) {
			pstmt.setInt(1,limit);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(setArticle(rs));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Article> selectNew(String page) {
		String selectNew = "SELECT a.art_id, u.uid,  u.u_name, art_title, art_content, art_po_time, art_like\r\n"
				+ "FROM\r\n" + "    FurrEver.articles a\r\n" + "    JOIN FurrEver.USER u ON a.art_user_id = u.uid\r\n"
				+ "WHERE\r\n" + "    art_status = '1'\r\n" + "ORDER BY\r\n" + "    art_po_time DESC\r\n" + "LIMIT ?";
		var list = new ArrayList<Article>();
		int pageNum = Integer.parseInt(page);
		int limit = 3;  // 跳過幾筆文章
		if(pageNum>1) {
			selectNew+=",3";  // 3 為頁面上顯示的文章數量
			limit = ((pageNum-1)*3); //第二頁跳過3筆
		}
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(selectNew);
				) {
			pstmt.setInt(1,limit);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				list.add(setArticle(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Article> search(String searchText) {
		String search = "SELECT a.art_id, u.uid,  u.u_name, art_title, art_content, art_po_time, art_like\r\n"
				+ "FROM\r\n" + "	FurrEver.articles a\r\n" + "    JOIN FurrEver.USER u ON a.art_user_id = u.uid\r\n"
				+ "WHERE art_status = '1' and art_title LIKE ?\r\n" + "ORDER BY art_like DESC\r\n" + "LIMIT ?";
		var list = new ArrayList<Article>();
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(search);) {
			pstmt.setString(1, "%" + searchText + "%");
			pstmt.setInt(2, 3);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(setArticle(rs));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArticlePic selectPic(String art_id) {
		String selectPic = "SELECT ap.pic_art_id, MIN(ap.pic_content) AS pic_content\r\n"
				+ "FROM FurrEver.articles_pics ap\r\n" + "where ap.pic_art_id = ?";
		
		ArticlePic articlePic = null;
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectPic);) {
			pstmt.setString(1, art_id);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next()) {
				articlePic = new ArticlePic();
//				String pic_content = new String(Base64.getEncoder().encode(rs.getBytes("pic_content")));
				articlePic.setPic_content(rs.getBytes("pic_content"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articlePic;
	}
	
	@Override
	public ArticlePic selectCarouselPic(String art_id, String picOrder) {
		String selectCarouselPic = "SELECT pic_content FROM FurrEver.articles_pics where pic_art_id = ? LIMIT 1 OFFSET ?";
		ArticlePic articlePic = null;
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectCarouselPic);) {
			pstmt.setString(1, art_id);
			pstmt.setInt(2, Integer.parseInt(picOrder));  // OFFSET 需要的是整數值...否則會報錯
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				articlePic = new ArticlePic();
//				String pic_content = new String(Base64.getEncoder().encode(rs.getBytes("pic_content")));
				articlePic.setPic_content(rs.getBytes("pic_content"));
				
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articlePic;
	}

	@Override
	public ArticlePic selectAvatar(String uid) {
		String selectAvatar = "SELECT u_pic from USER where uid = ?";

		ArticlePic articlePic = null;
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectAvatar);) {
			pstmt.setString(1, uid);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				articlePic = new ArticlePic();
				articlePic.setPic_content(rs.getBytes("u_pic"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articlePic;
	}
	
	@Override
	public List<Article> selectByArt_id(String art_id) {
		String selectByArt_id = "SELECT a.art_id, u.uid, u.u_name, art_title, art_content, art_po_time, art_like\r\n"
				+ "FROM\r\n" + "FurrEver.articles a\r\n" +"JOIN FurrEver.USER u ON a.art_user_id = u.uid\r\n"
				+ "WHERE\r\n" + "art_status = '1' and art_id=?";
		
		var list = new ArrayList<Article>();
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(selectByArt_id);
				) {
			pstmt.setString(1, art_id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(setArticle(rs));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Article setArticle(ResultSet rs) {
		
		Article article = new Article();
		try {
			article.setArt_id(rs.getInt("art_id"));
			article.setUid(rs.getInt("uid"));
			article.setU_name(rs.getString("u_name"));
			article.setArt_title(rs.getString("art_title"));
			article.setArt_content(rs.getString("art_content"));
			article.setArt_po_time(rs.getTimestamp("art_po_time"));
			article.setArt_like(rs.getInt("art_like"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return article;
	}

	@Override
	public String selectCountById(String order, String art_id) {
		String selectCountById = "";
		String count = "";
		if(order.equals("dnone")) {
			selectCountById ="SELECT count(*) FROM FurrEver.articles_pics GROUP BY pic_art_id HAVING pic_art_id = ?";
		}
		
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(selectCountById);
				) {
			pstmt.setString(1, art_id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				count = rs.getString("count(*)");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}


}