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


@WebServlet("/artComCount")
public class ArticleComCountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArticlesService service;

	@Override
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String com_art_id = request.getParameter("com_art_id");
		
		Integer comCount = service.selectComCount(Integer.parseInt(com_art_id));
		
		//將select方法拿到的List轉成json
		String json = ArticlesUtils.TurnIntoJson(comCount);
        // 告訴前端response為json格式 編碼為UTF-8
		response.setContentType("application/json; charset=UTF-8");
        // 寫出
        response.getWriter().write(json);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
