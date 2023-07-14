package articles.service;

import java.util.List;

import articles.dao.ArticlesDao;
import articles.dao.ArticlesDaoImpl;
import articles.vo.Article;
import articles.vo.ArticlePic;

public class ArticlesServiceImpl implements ArticlesService {
	private ArticlesDao dao;
	
	public ArticlesServiceImpl() {
		dao = new ArticlesDaoImpl();
	}

	@Override
	public List<Article> selectHot(String page) {
		return dao.selectHot(page);
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
		return dao.selectPic(art_id);
	}

	@Override
	public ArticlePic selectAvatar(String uid) {
		return dao.selectAvatar(uid);
	}

	@Override
	public List<Article> selectByArt_id(String art_id) {
		return dao.selectByArt_id(art_id);
	}
	}
