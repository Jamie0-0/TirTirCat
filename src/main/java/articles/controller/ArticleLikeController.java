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


@WebServlet("/artLike")
public class ArticleLikeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticlesService service;

	@Override
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
//		登入資訊		
//		HttpSession session = request.getSession();
//		String uid = (String) session.getAttribute("uid");
		
		String art_id = request.getParameter("art_id");
		String uid = request.getParameter("uid");
		int status = service.likeArticle(art_id, uid);
		
		String json = ArticlesUtils.TurnIntoJson(status);
		
		response.setContentType("application/json");
		response.getWriter().write(json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
