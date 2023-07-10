package articles.dao;

import java.util.List;

import articles.vo.Article;

public interface ArticlesDao {

	List<Article> selectHot();
	
	List<Article> selectNew();
	
	List<Article> search(String order);
}