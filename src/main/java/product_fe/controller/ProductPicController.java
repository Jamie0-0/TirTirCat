package product_fe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import product_fe.dao.ProductDao;
import product_fe.dao.ProductDaoImpl;
import product_fe.service.ProductService;
import product_fe.service.ProductServiceImpl;

@WebServlet("/productPic")
public class ProductPicController extends HttpServlet{
	
	private ProductService service;
	
	@Override
	public void init() throws ServletException {
		service = new ProductServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ProductDao dao = new ProductDaoImpl();
		
		int p_id = Integer.parseInt(req.getParameter("p_id"));
		byte[] pic_content = dao.selectPicByPid(p_id);
		
		resp.setContentType("image/gif, image/jpeg, image/png");
		ServletOutputStream out = resp.getOutputStream();
		out.write(pic_content);
		out.flush();
		out.close();
	}

}
