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
			String uid = request.getParameter("uid");
			List<Article> artList = null;
			String json ="";
			
//			if (art_id != null) {
//				System.out.println(art_id);
//				request.getRequestDispatcher("/article.html").forward(request, response);
//				return;
//			}
			
			// 判斷傳來的指令 熱門文章hot  最新文章new 搜尋文章search 讀取圖片getPic
			switch (order) {
			case "hot":
				artList = service.selectHot();
				break;
			case "new":
				artList = service.selectNew();
				break;
			case "search":
				artList = service.search(searchText.trim());
				break;
			case "getPic":
				ArticlePic articlePic = service.selectPic(art_id);
				sendPicToClient(articlePic.getPic_content(),response);
				return;   // 由於圖片讀取不用往下跑 return結束
//				json = TurnIntoJson(articlePic);   // 也可以用JSON傳法 前端要修改拿照片的方式
			case "getAvatar":
				ArticlePic avatarPic = service.selectAvatar(uid);
				sendPicToClient(avatarPic.getPic_content(),response);
				return;  
			}
			
			//將select方法拿到的List轉成json
			json = TurnIntoJson(artList);
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
	        outputStream.write(pic_content);  // 走IO直接輸出照片的byte[]到前端
	        outputStream.flush();
	        outputStream.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
