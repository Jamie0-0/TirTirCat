package articles.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import articles.service.ArticlesService;
import articles.service.ArticlesServiceImpl;


@WebServlet("/articleUpdate")
public class ArticleUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticlesService service;
	@Override
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		request.setCharacterEncoding("UTF-8");
		
		String art_id = request.getParameter("art_id");
		String art_title = request.getParameter("art_title");
		String art_content = request.getParameter("art_content");
		
		service.updateArticle(art_id, art_title, art_content);
			
	}

}
