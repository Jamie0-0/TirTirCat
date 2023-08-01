package product_fe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import product_fe.service.ProductService;
import product_fe.service.ProductServiceImpl;

@WebServlet("/shop/productDetail")
public class ProductDetailController extends HttpServlet{
	
	private ProductService service;
	
	@Override
	public void init() throws ServletException {
		service = new ProductServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		int p_id = Integer.parseInt(req.getParameter("p_id"));

		Gson gson = new Gson();
		var product = service.viewProductDetail(p_id);
		
		String message = gson.toJson("");
		
		message = "{\"status\":\"true\",\"viewProductDetail\":" + gson.toJson(product) + "}";
		resp.getWriter().write(message);
		
		System.out.println(message);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
