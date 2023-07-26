package articles.service;

import java.util.List;

import articles.vo.Article;
import articles.vo.ArticlePic;
import core.CoreService;

public interface ArticlesService extends CoreService{

	// select
	List<Article> selectHot(String page);

	List<Article> selectNew(String page);

	List<Article> search(String order);

	ArticlePic selectPic(String art_id);

	ArticlePic selectAvatar(String uid);

	List<Article> selectByArt_id(String art_id);

	ArticlePic selectCarouselPic(String art_id, String picOrder);

	String selectCountById(String order, String art_id);

	List<Article> selectAllArticles();
	
	List<String> getArticlesByTag(String tag);

	int selectPageCount(String type);

	// insert
	int insertArticle(String art_user_id, String art_title, String art_content, List<byte[]> imageList);
	
	// delete
	int deleteArtclePics(String pic_art_id);

	void jedisRefresh(); // 清掉redis

	void setArticlesTag(String tag);

	// update
	int updateArticle(String art_id, String art_title, String art_content, List<byte[]> imageList);

	Integer selectComCount(int com_art_id);

	int likeArticle(String art_id, String uid);

}