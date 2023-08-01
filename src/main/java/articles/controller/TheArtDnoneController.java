package articles.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import articles.service.ArticlesService;
import articles.service.ArticlesServiceImpl;


@WebServlet("/artDnone")
public class TheArtDnoneController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ArticlesService service;

	@Override
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String art_id = (String) session.getAttribute("art_id");
		String count = service.selectCountById("dnone",art_id);
		
		response.setContentType("plain/text; charset=UTF-8");
        // 寫出
        response.getWriter().write(count);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
