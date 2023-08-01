package product_fe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import product_fe.service.ProductService;
import product_fe.service.ProductServiceImpl;

@WebServlet("/shop/categories")
public class ShopCategoriesController extends HttpServlet{
	
	private ProductService service;
	
	@Override
	public void init() throws ServletException {
		service = new ProductServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		
		String how = req.getParameter("how");
		String keywords = req.getParameter("keywords");
		
		var list = service.selectByKeyWords(how, keywords);

		String message = gson.toJson("");
		
		message = "{\"status\":\"true\",\"selectByKeyWords\":" + gson.toJson(list) + "}";
		resp.getWriter().write(message);

		System.out.println(message);
		
	}

}
