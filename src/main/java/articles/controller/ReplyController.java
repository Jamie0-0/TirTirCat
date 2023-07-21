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
import articles.vo.Reply;


@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentService service;
       
	@Override
	public void init() throws ServletException {
		service = new CommentServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Reply> replyList = null;

		String reply_com_id = request.getParameter("reply_com_id");
 		replyList = service.selectReply(reply_com_id);

		String json = ArticlesUtils.TurnIntoJson(replyList);

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
