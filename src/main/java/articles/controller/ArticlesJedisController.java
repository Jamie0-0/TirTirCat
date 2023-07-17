package articles.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import articles.service.ArticlesService;
import articles.service.ArticlesServiceImpl;
import articles.vo.Article;

@WebServlet("/ArticlesJedis")
public class ArticlesJedisController extends HttpServlet {
	private ArticlesService service;
	
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tag = "";
		
		if(service.getArticlesByTag(tag) == null){
			List<Article> artList = service.selectAllArticles();
			
			service.setArticlesTag(articles);
		}
		
		service.getArticlesByTag(String tag);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
