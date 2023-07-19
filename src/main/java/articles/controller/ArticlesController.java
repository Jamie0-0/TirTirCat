package articles.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import articles.service.ArticlesService;
import articles.service.ArticlesServiceImpl;
import articles.vo.Article;
import articles.vo.ArticlePic;

import articles.ariclesUtils.*;

@WebServlet("/forum")
public class ArticlesController extends HttpServlet {

	private ArticlesService service;

	@Override
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			request.setCharacterEncoding("UTF-8");
			String order = request.getParameter("order");
			String page = request.getParameter("page");
			String art_id = request.getParameter("art_id");
			List<Article> artList = null;
			
			// 判斷傳來的指令 熱門文章hot  最新文章new
			// 搜尋文章search 讀取圖片getPic
			switch (order) {
			case "hot":
				artList = service.selectHot(page);
				break;
			case "new":
				artList = service.selectNew(page);
				break;
			case "search":
				String searchText = request.getParameter("searchText");
				artList = service.search(searchText.trim());
				break;
			case "getPic":
				ArticlePic articlePic = service.selectPic(art_id);
				ArticlesUtils.sendPicToClient(articlePic.getPic_content(),response);
				return;
			case "getAvatar":
				String uid = request.getParameter("uid");
				ArticlePic avatarPic = service.selectAvatar(uid);
				ArticlesUtils.sendPicToClient(avatarPic.getPic_content(),response);
				return;
			case "article":
				 HttpSession session = request.getSession();
				 session.setAttribute("art_id",art_id);
				 System.out.println("收到art_id並forward:"+art_id);
				 request.getRequestDispatcher("/comment").forward(request, response);
				 return;
				 default:
					 System.out.println("不明錯誤");
			}
			
			//將select方法拿到的List轉成json
			String json = ArticlesUtils.TurnIntoJson(artList);
	        // 告訴前端response為json格式
	        response.setContentType("application/json");
	        // 設定編碼
	        response.setCharacterEncoding("UTF-8");
	        // 寫出
	        response.getWriter().write(json);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
