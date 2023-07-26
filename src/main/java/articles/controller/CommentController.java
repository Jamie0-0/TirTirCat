package articles.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import articles.ariclesUtils.ArticlesUtils;
import articles.service.CommentService;
import articles.service.CommentServiceImpl;
import articles.vo.Comment;

@WebServlet("/comment")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService service;

	@Override
	public void init() throws ServletException {
		service = new CommentServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Comment> commentList = null;

		HttpSession session = request.getSession();
		String com_art_id = (String) session.getAttribute("art_id");
		commentList = service.selectComment(com_art_id);

		String json = ArticlesUtils.TurnIntoJson(commentList);

		response.setContentType("application/json; charset=UTF-8");
		// 寫出
		response.getWriter().write(json);
	}

}
