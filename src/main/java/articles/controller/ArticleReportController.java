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

@WebServlet("/artReport")
public class ArticleReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticlesService service;

	@Override
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String rep_art_id = request.getParameter("rep_art_id");
		String crep_com_id = request.getParameter("crep_com_id");
		String rrep_reply_id = request.getParameter("rrep_reply_id");
		String uid = request.getParameter("uid");
		String rep_reason = request.getParameter("rep_reason");

		int status = service.artReport(rep_art_id, crep_com_id, rrep_reply_id, uid, rep_reason);

		String json = ArticlesUtils.TurnIntoJson(status);

		response.setContentType("application/json");
		response.getWriter().write(json);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
