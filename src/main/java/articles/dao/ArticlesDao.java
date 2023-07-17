package articles.dao;

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
}