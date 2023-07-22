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

import articles.service.ArticlesService;
import articles.service.ArticlesServiceImpl;

@WebServlet("/ArtInsert")
@MultipartConfig
public class ArticleInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticlesService service;

	@Override
	public void init() throws ServletException {
		service = new ArticlesServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String art_user_id = request.getParameter("art_user_id");
		String art_title = request.getParameter("art_title");
		String art_content = request.getParameter("art_content");
		
		System.out.println("art_user_id="+art_user_id);
		System.out.println("art_title"+art_title);
		System.out.println("art_content"+art_content);
		
        List<byte[]> imageList = new ArrayList<>();

        // 獲取前端傳遞的多個 Part，即多張照片
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
		
        // 獲取前端傳遞的 base64 編碼的圖片數據
//      String base64ImageData = request.getParameter("imageData");

      // 將 base64 編碼的數據解碼為 byte[]
//		  byte[] imageData = Base64.getDecoder().decode(base64ImageData);

      // 在這裡可以將 byte[] 保存到文件中或進行其他處理
		
		
		
		String status = service.insertArticle(art_user_id, art_title, art_content,imageList);
		


        // 返回回應給前端
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(status);
	}

}
