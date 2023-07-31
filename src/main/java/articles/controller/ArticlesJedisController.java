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

	private static final long serialVersionUID = 1L;
	private ArticlesService service;
	
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String tag = "";
		
		
		service.setArticlesTag(tag);

		// 標籤搜尋
		service.getArticlesByTag(tag);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
