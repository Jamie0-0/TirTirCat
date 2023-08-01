package product_fe.controller;

import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product_fe.service.ProductService;
import product_fe.service.ProductServiceImpl;

@WebServlet("/shop")
public class ShopController extends HttpServlet {

	private ProductService service;

	@Override
	public void init() throws ServletException {
		service = new ProductServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		Gson gson = new Gson();
		var list = service.findAllForShopList();	

		String message = gson.toJson("");
		message = "{\"status\":\"true\",\"findAllForShopList\":" + gson.toJson(list) + "}";
		resp.getWriter().write(message);

		System.out.println(message);

	}

}
