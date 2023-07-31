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

@WebServlet("/forumPage")
public class PageCountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private ArticlesService service;

	@Override
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String searchText = request.getParameter("searchText");
		int page = service.selectPageCount(searchText);
		
		// 轉成json
		String json = ArticlesUtils.TurnIntoJson(page);
      
		response.setContentType("application/json; charset=UTF-8");
        // 寫出
        response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
