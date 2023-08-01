package articles.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import articles.service.ArticlesService;
import articles.service.ArticlesServiceImpl;
import articles.vo.Article;
import articles.ariclesUtils.*;

@WebServlet("/article")
public class TheArticleController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ArticlesService service;

	@Override
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 由forum.html的繼續閱讀進來
		HttpSession session = request.getSession();
		String art_id = (String) session.getAttribute("art_id");
		List<Article> artList = null;
		
		if (art_id != null) {
			artList = service.selectByArt_id(art_id);
			if(artList.isEmpty()) {
				System.out.println("找不到此文章，送回forum.html");
				artList = null;   // artList為null傳給前端，前端收到null跳轉回forum.html
			}
		}

		// 將select方法拿到的List轉成json
		String json = ArticlesUtils.TurnIntoJson(artList);
		response.setContentType("application/json; charset=UTF-8");
		// 寫出
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
