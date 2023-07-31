package articles.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import articles.service.ArticlesService;
import articles.service.ArticlesServiceImpl;
import articles.vo.ArticlePic;

import articles.ariclesUtils.*;

@WebServlet("/avatar")
public class AvatarController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ArticlesService service;

	@Override
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}

//
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String uid = request.getParameter("uid");
		ArticlePic avatarPic = service.selectAvatar(uid);
		ArticlesUtils.sendPicToClient(avatarPic.getPic_content(), response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
