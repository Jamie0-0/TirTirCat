package articles.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import articles.service.ArticlesService;
import articles.service.ArticlesServiceImpl;
import articles.vo.Article;
import articles.vo.ArticlePic;

@WebServlet("/articles/controller/ArticlesController")
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
			String art_id = request.getParameter("art_id");
			String searchText = request.getParameter("searchText");
			String json ="";
			List<Article> artList;
//			if (art_id != null) {
//				System.out.println(art_id);
//				request.getRequestDispatcher("/article.html").forward(request, response);
//				return;
//			}
			System.out.println("====================");

			switch (order) {
			case "hot":
				artList = service.selectHot();
				json = TurnIntoJson(artList);
				break;
			case "new":
				artList = service.selectNew();
				json = TurnIntoJson(artList);
				break;
			case "search":
				artList = service.search(searchText.trim());
				json = TurnIntoJson(artList);
				break;
			case "getPic":
				System.out.println("getPic");
				System.out.println(art_id);
				ArticlePic articlePic = service.selectPic(art_id);
				sendPicToClient(articlePic.getPic_content(),response);
				return;
//				json = TurnIntoJson(articlePic);
			}

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

	public String TurnIntoJson(Object object) {
		Gson gson = new Gson();
		return  gson.toJson(object);
	}
	public void sendPicToClient(byte[] pic_content, HttpServletResponse response) {
	    try {
	       ServletOutputStream outputStream = response.getOutputStream();
	        response.setContentType("image/jpeg, image/jpg, image/png, image/gif"); 
	        outputStream.write(pic_content);
	        outputStream.flush();
	        outputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
