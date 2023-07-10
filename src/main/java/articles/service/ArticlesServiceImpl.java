package articles.service;

import java.util.List;

import articles.dao.ArticlesDao;
import articles.dao.ArticlesDaoImpl;
import articles.vo.Article;

public class ArticlesServiceImpl implements ArticlesService {
	private ArticlesDao dao;
	
	public ArticlesServiceImpl() {
		dao = new ArticlesDaoImpl();
	}

	@Override
	public List<Article> selectHot() {
		return dao.selectHot();
	}
	@Override
	public List<Article> selectNew() {
		return dao.selectNew();
	}

	@Override
	public List<Article> search(String order) {
		return dao.search(order);
	}
}
