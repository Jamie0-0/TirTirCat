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
import articles.vo.ArticlePic;
import articles.ariclesUtils.*;

@WebServlet("/carousel")
public class TheArticlePicController extends HttpServlet {

	private ArticlesService service;

	@Override
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String art_id = (String) session.getAttribute("art_id");
		System.out.println("art_id"+art_id);
		String picOrder = request.getParameter("picOrder");
		System.out.println("picOrder"+picOrder);
		ArticlePic articlePic = service.selectCarouselPic(art_id, picOrder);
		if(articlePic !=null) {
			ArticlesUtils.sendPicToClient(articlePic.getPic_content(), response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
