package articles.dao;

import java.sql.Connection;
import java.util.List;

import articles.vo.Article;
import articles.vo.ArticlePic;

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

	List<Article> selectHotRedis(String page);

	void saveHotArticlesToRedis(List<Article> hotArticles);

	List<Article> selectAllHot();

	ArticlePic selectRedisPic(String art_id);

	void savePicToRedis(String art_id, ArticlePic articlePic);

	ArticlePic selectRedisAvatar(String uid);

	void saveAvatarToRedis(String uid, ArticlePic AvatarPic);

	String insertArticle(String art_user_id, String art_title, String art_content);

	String insertArticlePic(String pic_art_id, List<byte[]> imageList);

	int deleteArticlePics(String pic_art_id);

	void jedisRefresh();

	void setArticlesTag(String tag);

	void jedisPicRefresh(String pic_art_id);

	int updateArticle(Article newArt);

	Integer selectComCount(int com_art_id);
}