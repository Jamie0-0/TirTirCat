package articles.dao;

import java.util.List;

import articles.vo.Article;
import articles.vo.ArticlePic;

public interface ArticlesDao {

	List<Article> selectHot();
	
	List<Article> selectNew();
	
	List<Article> search(String order);

	ArticlePic selectPic(String art_id);

	ArticlePic selectAvatar(String uid);
}