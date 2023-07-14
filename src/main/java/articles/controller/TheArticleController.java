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

@WebServlet("/articles/controller/TheArticleController")
public class TheArticleController extends HttpServlet {

	private ArticlesService service;

	@Override
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String art_id = (String) session.getAttribute("art_id");
		List<Article> artList = null;
		if (art_id != null) {
			System.out.println(art_id);
			artList = service.selectByArt_id(art_id);
		} else {
			// artList為null傳給前端，前端收到null跳轉回forum.html
		}
		// 將select方法拿到的List轉成json
		String json = ArticlesController.TurnIntoJson(artList);
		// 告訴前端response為json格式
		response.setContentType("application/json");
		// 設定編碼
		response.setCharacterEncoding("UTF-8");
		// 寫出
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
