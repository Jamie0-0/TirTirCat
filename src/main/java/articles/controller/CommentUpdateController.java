package articles.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import articles.service.CommentService;
import articles.service.CommentServiceImpl;
import articles.service.ReplyService;
import articles.service.ReplyServiceImpl;

@WebServlet("/commentUpdate")
public class CommentUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService service;

	@Override
	public void init() throws ServletException {
		service = new CommentServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String com_id = request.getParameter("com_id");
		String com_content = request.getParameter("com_content");

		System.out.println("reply_com_id=" + com_id);
		System.out.println("reply_content=" + com_content);

		String status =  service.updateComment(com_id, com_content);

		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(status);
	}

}
