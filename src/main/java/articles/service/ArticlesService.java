package articles.service;

import java.util.List;

import articles.vo.Article;

public interface ArticlesService {

	List<Article> selectHot();

	List<Article> selectNew();

	List<Article> search();
}