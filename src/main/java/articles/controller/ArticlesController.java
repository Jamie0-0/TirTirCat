package articles.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;

import articles.service.ArticlesService;
import articles.service.ArticlesServiceImpl;
import articles.vo.Article;

@WebServlet("/articles/controller/ArticlesController")
public class ArticlesController extends HttpServlet {
	
	private ArticlesService service;
	
	@Override
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		
		String order = request.getParameter("order");
		System.out.println(order);
		
		List<Article> ArtList; 	
		
		if(order.equals("hot")) {
			ArtList = service.selectHot();
		}else {
			ArtList = service.selectNew();
		}
		
		// 用 gson物件的toJson方法把ArtList轉成json物件
		Gson gson = new Gson();
	    String json = gson.toJson(ArtList);
        
        // 告訴前端response為json格式
        response.setContentType("application/json");
        // 設定編碼
        response.setCharacterEncoding("UTF-8");
        
        // 寫出
        response.getWriter().write(json);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
