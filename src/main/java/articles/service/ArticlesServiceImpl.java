package articles.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import articles.dao.ArticlesDao;
import articles.dao.ArticlesDaoImpl;
import articles.vo.Article;
import articles.vo.ArticlePic;
import redis.clients.jedis.exceptions.JedisException;

public class ArticlesServiceImpl implements ArticlesService {
	private ArticlesDao dao;

	public ArticlesServiceImpl() {
		dao = new ArticlesDaoImpl();
	}
// 查詢功能

	@Override
	public List<Article> selectHot(String page) {
		List<Article> list = null;
		try {
			list = dao.selectHotRedis(page);
			if (list == null || list.isEmpty()) {
				list = dao.selectAllHot();
				dao.saveHotArticlesToRedis(list); // 把熱門全部存進去
				return dao.selectHot(page);
			}
		} catch (JedisException e) {
			System.out.println("selectHotRedis錯誤");
			e.printStackTrace();
			list = dao.selectHot(page);
		} catch (Exception e) {
			System.out.println("selectHot其他錯誤");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Article> selectNew(String page) {
		return dao.selectNew(page);
	}

	@Override
	public List<Article> search(String order) {
		return dao.search(order);
	}

	@Override
	public ArticlePic selectPic(String art_id) {

		ArticlePic articlePic = null;
		try {
			articlePic = dao.selectRedisPic(art_id);
			if (dao.selectRedisPic(art_id).getPic_content() == null) { // 注意不是dao.selectRedisPic(art_id) == null
				articlePic = dao.selectPic(art_id);
				dao.savePicToRedis(art_id, articlePic);
			}

		} catch (JedisException e) {
			System.out.println("selectRedisPic錯誤");
			e.printStackTrace();
			return dao.selectPic(art_id);
		} catch (Exception e) {
			System.out.println("selectPic其他錯誤");
			e.printStackTrace();
		}
		return articlePic;
	}

	@Override
	public ArticlePic selectAvatar(String uid) {
		ArticlePic avatarPic = null;
		try {
			avatarPic = dao.selectRedisAvatar(uid);
			if (dao.selectRedisAvatar(uid).getPic_content() == null) { // 注意不是dao.selectRedisAvatar(uid) == null
				avatarPic = dao.selectAvatar(uid);
				dao.saveAvatarToRedis(uid, avatarPic);
			}
		} catch (JedisException e) {
			System.out.println("selectRedisAvatar錯誤");
			e.printStackTrace();
			avatarPic = dao.selectAvatar(uid);
		} catch (Exception e) {
			System.out.println("selectAvatar其他錯誤");
			e.printStackTrace();
		}
		return avatarPic;
	}

	@Override
	public List<Article> selectByArt_id(String art_id) {
		return dao.selectByArt_id(art_id);
	}

	@Override
	public ArticlePic selectCarouselPic(String art_id, String picOrder) {

		return dao.selectCarouselPic(art_id, picOrder);
	}

	@Override
	public String selectCountById(String order, String art_id) {

		return dao.selectCountById(order, art_id);
	}

	@Override
	public List<Article> selectAllArticles() {
		return null;
	}

	@Override
	public List<String> getArticlesByTag(String tag) {
		return null;
	}

	@Override
	public int selectPageCount(String searchText) {
		int count = 0;

		if (searchText != null && searchText.trim() != "") {
			count = dao.selectPageSearchCount(searchText);
		} else {
			count = dao.selectPageCount();
		}
		return count;
	}

	// 查詢功能 end
	// 新增功能

	@Override
	public String insertArticle(String art_user_id, String art_title, String art_content, List<byte[]> imageList) {

		DataSource ds = null;
		Connection conn = null;
		String status = "新增失敗";
		try {
			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FurrEver");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);

			String pic_art_id = dao.insertArticle(art_user_id, art_title, art_content, conn); // 把conn傳到dao層
			if (!pic_art_id.equals("")) {
				status = dao.insertArticlePic(pic_art_id, imageList, conn);
			}

			conn.commit();
			status = "新增成功";

		} catch (SQLException e) {
			System.out.println(" insertArticle錯了");
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}

		return status;
	}

	@Override
	public String deleteArtclePics(String pic_art_id) {

		String status = dao.deleteArticlePics(pic_art_id);

		return status;
	}
	// 新增功能 end
	// delete

	// delete end

	// jedis refresh
	@Override
	public void jedisRefresh() {
		dao.jedisRefresh();
	}

	// jedis tag
	@Override
	public void setArticlesTag(String tag) {
		dao.setArticlesTag(tag);

	}

	@Override
	public int updateArticle(String art_id, String art_title, String art_content) {
		
		int statusCode = 0;
		
		try {
	
		beginTransaction();
		Article article = new Article();
		article.setArt_id(Integer.parseInt(art_id));
		article.setArt_title(art_title);
		article.setArt_content(art_content);
	
		statusCode = dao.updateArticle(article);
		commit();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusCode;
	}

}
