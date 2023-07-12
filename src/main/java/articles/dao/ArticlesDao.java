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
}