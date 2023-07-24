package articles.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import articles.ariclesUtils.ArticlesUtils;
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
		
		int status = service.updateArticle(art_id, art_title, art_content);
		
		
		String statusString = ArticlesUtils.TurnIntoJson(status);   // 如果不轉成json 前端會有問題

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(statusString);
	}

}
