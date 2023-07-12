package articles.service;

import java.util.List;

import articles.vo.Article;
import articles.vo.ArticlePic;

public interface ArticlesService {

	List<Article> selectHot(String page);

	List<Article> selectNew(String page);

	List<Article> search(String order);

	ArticlePic selectPic(String art_id);

	ArticlePic selectAvatar(String uid);
}