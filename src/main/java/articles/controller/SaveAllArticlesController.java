package articles.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import articles.service.ArticlesService;
import articles.service.ArticlesServiceImpl;

/**
 * Servlet implementation class SaveAllArticlesController
 */
@WebServlet("/saveAllArt")
public class SaveAllArticlesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticlesService service;

	@Override
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service.saveAllHotArticles();
		service.saveAllNewArticles();
		System.out.println("存取文章成功");

	}

}
