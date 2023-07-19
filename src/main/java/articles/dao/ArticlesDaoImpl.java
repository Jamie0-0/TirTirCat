package articles.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import articles.ariclesUtils.JedisPoolUtil;
import articles.vo.Article;
import articles.vo.ArticlePic;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ArticlesDaoImpl implements ArticlesDao {
	private DataSource ds;

	public ArticlesDaoImpl() {

		try {
			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FurrEver");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	// select
	@Override
	public List<Article> selectHot(String page) {
		String selectHot = "SELECT a.art_id, u.uid, u.u_name, art_title, art_content, art_po_time, art_like\r\n"
				+ "FROM\r\n" + "    FurrEver.articles a\r\n" + "    JOIN FurrEver.USER u ON a.art_user_id = u.uid\r\n"
				+ "WHERE\r\n" + "    art_status = '1'\r\n" + "ORDER BY\r\n" + "  art_like desc\r\n" + "LIMIT ?";

		var list = new ArrayList<Article>();
		int pageNum = Integer.parseInt(page);
		int limit = 3; // 跳過幾筆文章
		if (pageNum > 1) {
			selectHot = selectHot + ",3"; // 3 為頁面上顯示的文章數量
			limit = ((pageNum - 1) * 3); // 第二頁跳過3筆
		}
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectHot);) {
			pstmt.setInt(1, limit);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(setArticle(rs));
			}
			rs.close();
			pstmt.close();
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
		int limit = 3; // 跳過幾筆文章
		if (pageNum > 1) {
			selectNew += ",3"; // 3 為頁面上顯示的文章數量
			limit = ((pageNum - 1) * 3); // 第二頁跳過3筆
		}
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectNew);) {
			pstmt.setInt(1, limit);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(setArticle(rs));
			}
			rs.close();
			pstmt.close();
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
			pstmt.close();
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

			while (rs.next()) {
				articlePic = new ArticlePic();
//				String pic_content = new String(Base64.getEncoder().encode(rs.getBytes("pic_content")));
				articlePic.setPic_content(rs.getBytes("pic_content"));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articlePic;
	}

	@Override
	public ArticlePic selectCarouselPic(String art_id, String picOrder) {
		String selectCarouselPic = "SELECT pic_content FROM FurrEver.articles_pics where pic_art_id = ? LIMIT 1 OFFSET ?";
		ArticlePic articlePic = null;
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(selectCarouselPic);) {
			pstmt.setString(1, art_id);
			pstmt.setInt(2, Integer.parseInt(picOrder)); // OFFSET 需要的是整數值...否則會報錯
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				articlePic = new ArticlePic();
//				String pic_content = new String(Base64.getEncoder().encode(rs.getBytes("pic_content")));
				articlePic.setPic_content(rs.getBytes("pic_content"));

			}
			rs.close();
			pstmt.close();
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
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articlePic;
	}

	@Override
	public List<Article> selectByArt_id(String art_id) {
		String selectByArt_id = "SELECT a.art_id, u.uid, u.u_name, art_title, art_content, art_po_time, art_like\r\n"
				+ "FROM\r\n" + "FurrEver.articles a\r\n" + "JOIN FurrEver.USER u ON a.art_user_id = u.uid\r\n"
				+ "WHERE\r\n" + "art_status = '1' and art_id=?";

		var list = new ArrayList<Article>();
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectByArt_id);) {
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
		if (order.equals("dnone")) {
			selectCountById = "SELECT count(*) FROM FurrEver.articles_pics GROUP BY pic_art_id HAVING pic_art_id = ?";
		}

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectCountById);) {
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

	@Override
	public int selectPageCount() {
		String selectPageCount = "SELECT count(*) FROM FurrEver.articles WHERE art_status = '1'";
		int count = 0;

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(selectPageCount);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				count = rs.getInt("count(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public int selectPageSearchCount(String searchText) {
		String selectPageSearchCount = "SELECT count(*) FROM FurrEver.articles WHERE art_status = '1' AND art_title LIKE ?";
		int count = 0;
		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(selectPageSearchCount);) {
			pstmt.setString(1, "%" + searchText + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("count(*)");
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<Article> selectHotRedis(String page) {

		List<Article> hotArticles = new ArrayList<>();

		JedisPool pool = JedisPoolUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		try {

			int pageOrder = Integer.parseInt(page);
			for (int i = 3 * (pageOrder - 1); i <= 3 * pageOrder - 1; i++) {
				String jsonString = jedis.lindex("hot", i);
				if (jsonString == null) {
					return hotArticles;
				}
				// 反序列化 JSON 字符串為 Article 物件
				ObjectMapper mapper = new ObjectMapper();
				Article article = mapper.readValue(jsonString, Article.class);
				hotArticles.add(article);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}

		return hotArticles;
	}

	@Override
	public List<Article> selectAllHot() {
		String selectHot = "SELECT a.art_id, u.uid, u.u_name, art_title, art_content, art_po_time, art_like\r\n"
				+ "FROM\r\n" + "    FurrEver.articles a\r\n" + "    JOIN FurrEver.USER u ON a.art_user_id = u.uid\r\n"
				+ "WHERE\r\n" + "    art_status = '1'\r\n" + "ORDER BY\r\n" + "  art_like desc\r\n";

		var list = new ArrayList<Article>();

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(selectHot);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				list.add(setArticle(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void saveHotArticlesToRedis(List<Article> hotArticles) {
		JedisPool pool = JedisPoolUtil.getJedisPool();
		Jedis jedis;

		// 初始化 Jedis 連接
		jedis = pool.getResource();

		// 將List<Article>序列化成JSON字符串
		ObjectMapper mapper = new ObjectMapper();
		try {

			for (Article article : hotArticles) {
				String jsonList = mapper.writeValueAsString(article);
				// 將JSON字符串存儲到Redis的List中
				jedis.rpush("hot", jsonList);
			}

		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		jedis.close();
	}

	@Override
	public ArticlePic selectRedisPic(String art_id) {
		ArticlePic articlePic = new ArticlePic();
		String key = "pic_art_id:"+art_id;
		JedisPool pool = JedisPoolUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		byte[] articlePicData = jedis.get(key.getBytes());
		articlePic.setPic_content(articlePicData);

		jedis.close();
		return articlePic;
	}

	@Override
	public void savePicToRedis(String art_id, ArticlePic articlePic) {
		String key = "pic_art_id:"+art_id;
		JedisPool pool = JedisPoolUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		byte[] articlePicData =articlePic.getPic_content();
		jedis.set(key.getBytes(), articlePicData);

		jedis.close();
	}

	@Override
	public ArticlePic selectRedisAvatar(String uid) {
		ArticlePic avatarPic = new ArticlePic();
		String key = "user_avatar:"+uid;
		JedisPool pool = JedisPoolUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		byte[] avatarPicData = jedis.get(key.getBytes());
		avatarPic.setPic_content(avatarPicData);

		jedis.close();
		return avatarPic;
	}

	@Override
	public void saveAvatarToRedis(String uid, ArticlePic avatarPic) {
		
		String key = "user_avatar:"+uid;
		JedisPool pool = JedisPoolUtil.getJedisPool();
		Jedis jedis = pool.getResource();
		byte[] avatarPicData =avatarPic.getPic_content();
		jedis.set(key.getBytes(), avatarPicData);

		jedis.close();
	
	}
	// select end
	
	// insert
	@Override
	public String insertArticle(String art_user_id, String art_title, String art_content, Connection conn) {
		

		String insertArticle = "INSERT INTO FurrEver.articles (art_user_id, art_title, art_content, art_po_time, art_like, art_rep_count, art_status)\r\n"
				+ "VALUES (?, ?, ?, NOW(), 0, 0, '1');";
		String gKey = "";
		try ( PreparedStatement pstmt = conn.prepareStatement(insertArticle, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, art_user_id);
			pstmt.setString(2, art_title);
			pstmt.setString(3, art_content);
			
			int generatedKey = 0;
			int rowCount = pstmt.executeUpdate();
			
			System.out.println(rowCount + "筆新增成功");
			
	        try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	               generatedKey = generatedKeys.getInt(1); 
	                System.out.println("Generated primary key: " + generatedKey);
	                gKey = Integer.toString(generatedKey);
	            } else {
	            }
	        }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gKey;

	}

	@Override
	public String insertArticlePic(String pic_art_id, List<byte[]> imageList, Connection conn) {
		String status = "新增失敗";
		String insertArticlePic = "INSERT INTO FurrEver.articles_pics (pic_content, pic_art_id) VALUES (?, ?);";
		int picArt_id = Integer.parseInt(pic_art_id);
		try (PreparedStatement pstmt = conn.prepareStatement(insertArticlePic);) {

			for (int i = 0; i < imageList.size(); i++) {
			    pstmt.setBytes(1, imageList.get(i));
			    pstmt.setInt(2, picArt_id);
			    pstmt.addBatch(); 
			}
			int[] rowCount = pstmt.executeBatch();
			status = "新增圖片成功";
			System.out.println(status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	
	// delete
	@Override
	public String deleteArticlePics(String pic_art_id) {
		String status = "刪除失敗";
		String sql = "DELETE FROM FurrEver.articles_pics WHERE pic_art_id = ? ";
		int picArt_id = Integer.parseInt(pic_art_id);
		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			 pstmt.setInt(1, picArt_id);
			 
			int rowCount = pstmt.executeUpdate();
			status = "刪除圖片成功";
			System.out.println(status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
	// delete end
	
}



