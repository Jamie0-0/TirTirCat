package articles.dao;

import java.util.List;

import articles.vo.Article;
import articles.vo.ArticlePic;
import articles.vo.ArticlesLike;

public interface ArticlesDao {

	List<Article> selectHot(String page);
	
	List<Article> selectNew(String page);
	
	List<Article> search(String searchText);

	ArticlePic selectPic(String art_id);

	ArticlePic selectAvatar(String uid);

	List<Article> selectByArt_id(String art_id);

	ArticlePic selectCarouselPic(String art_id, String picOrder);

	String selectCountById(String order, String art_id);

	int selectPageCount();

	int selectPageSearchCount(String searchText);

	void saveHotArticlesToRedis(List<Article> hotArticles);
	
	void saveNewArticlesToRedis(List<Article> newArticles);

	List<Article> selectAllHot();

	ArticlePic selectRedisPic(String art_id);

	void savePicToRedis(String art_id, ArticlePic articlePic);

	ArticlePic selectRedisAvatar(String uid);

	void saveAvatarToRedis(String uid, ArticlePic AvatarPic);

	int insertArticle(String art_user_id, String art_title, String art_content);

	int insertArticlePic(int pic_art_id, List<byte[]> imageList);

	int deleteArticlePics(String pic_art_id);

	void setArticlesTag(String tag);

	void jedisPicRefresh(String pic_art_id);

	int updateArticle(Article newArt);

	Integer selectComCount(int com_art_id);

	int likeArticle(int art_id);
	
	void insertArticleLike(int like_articles_id, int like_user_id);

	ArticlesLike selectLike(int artId, int userId);

	int unLikeArticle(ArticlesLike articlesLike);

	List<Article> selectAllNew();

	List<Article> selectRedis(String page, String key);

	void reportArt(Integer id, int userId, String repReason);

	void reportCrep(Integer id, int userId, String repReason);

	void reportRrep(Integer id, int userId, String repReason);

	void jedisRefresh();

}