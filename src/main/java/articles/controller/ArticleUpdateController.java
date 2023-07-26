package articles.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import articles.ariclesUtils.ArticlesUtils;
import articles.service.ArticlesService;
import articles.service.ArticlesServiceImpl;


@WebServlet("/articleUpdate")
@MultipartConfig
public class ArticleUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticlesService service;
	@Override
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		request.setCharacterEncoding("UTF-8");
		
		String art_id = request.getParameter("art_id");
		String art_title = request.getParameter("art_title");
		String art_content = request.getParameter("art_content");
		
		List<byte[]> imageList = new ArrayList<>();
		
        List<Part> imageParts = new ArrayList<>();
        for (Part part : request.getParts()) {
            if (part.getName().startsWith("image")) {
                imageParts.add(part);
            }
        }

        // 將每個 Part 的照片數據轉換為 byte[]
        for (Part imagePart : imageParts) {
            InputStream inputStream = imagePart.getInputStream();
            byte[] imageData = inputStream.readAllBytes();
            imageList.add(imageData);
            inputStream.close();
        }
        System.out.println("總共幾張照片="+imageList.size());

		
		int status = service.updateArticle(art_id, art_title, art_content, imageList);
		
		
		String json = ArticlesUtils.TurnIntoJson(status);   // 如果不轉成json 前端會有問題
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().write(json);
	}

}
