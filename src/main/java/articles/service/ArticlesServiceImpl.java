package articles.service;

import java.util.List;

import articles.dao.ArticlesDao;
import articles.dao.ArticlesDaoImpl;
import articles.vo.Article;
import articles.vo.ArticlePic;
import articles.vo.ArticlesLike;
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
	public Integer selectComCount(int com_art_id) {
		
		return dao.selectComCount(com_art_id);
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
	public int insertArticle(String art_user_id, String art_title, String art_content, List<byte[]> imageList) {
		int status = 0;
		int pic_art_id = dao.insertArticle(art_user_id, art_title, art_content);
		if (pic_art_id != 0) {
			status = dao.insertArticlePic(pic_art_id, imageList);
		} else {
			System.out.println("新增文章失敗");
		}
		return status;
	}

	@Override
	public int deleteArtclePics(String pic_art_id) {

		int status = dao.deleteArticlePics(pic_art_id);

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
	
	// update

	@Override
	public int updateArticle(String art_id, String art_title, String art_content, List<byte[]> imageList) {
		
		int status = 0;
		int artId = Integer.parseInt(art_id);
		
		try {
	
		beginTransaction();
		Article article = new Article();
		article.setArt_id(artId);
		article.setArt_title(art_title);
		article.setArt_content(art_content);
	
		dao.updateArticle(article);
		dao.jedisRefresh();
		if(imageList.size() != 0) {
		dao.deleteArticlePics(art_id);
		dao.insertArticlePic(artId, imageList);
		dao.jedisPicRefresh(art_id);
		}
		commit();
		status = 1;
		
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
		}
		return status;
	}
	
	@Override
	public int likeArticle(String art_id, String uid) {
		
		int artId = Integer.parseInt(art_id);
		int userId = Integer.parseInt(uid);
		int result = -1;
		
		try {
		
			beginTransaction();
			
			// 先查
			ArticlesLike articlesLike = dao.selectLike(artId, userId);
			
			if (articlesLike != null) {
				result = dao.unLikeArticle(articlesLike);
				
				System.out.println("收回讚成功");
				
			} else {
				// table: articles_like
				dao.insertArticleLike(artId, userId);
				
				// table: articles
				result = dao.likeArticle(artId);

				System.out.println("按讚成功");
			}
			
			

			
			commit();
			
			
		
		} catch (Exception e) {
			System.out.println("按讚或收回讚失敗");
			e.printStackTrace();
			rollback();
		}
		
		return result;
	}
	// update end


}
