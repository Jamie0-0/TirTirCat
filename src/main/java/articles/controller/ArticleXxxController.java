package articles.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/articleXxx")
public class ArticleXxxController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Override
	public void init() throws ServletException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String art_id = request.getParameter("art_id");
		session.setAttribute("art_id", art_id); // 由於article.html頁面載入會發請求到別的controller

		if (art_id != null) {
			System.out.println("送去文章號碼:"+art_id);
			response.sendRedirect("/TirTirCat/article.html");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
