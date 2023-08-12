package order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.dao.ProductOrderDao;
import order.dao.ProductOrderDaoImpl;
import order.service.ProductOrderService;
import order.service.ProductOrderServiceImpl;
import order.vo.ProductOrder;

@WebServlet("/userOrder")
public class UserOrderController extends HttpServlet{

	private ProductOrderService service;
	private ProductOrderDao dao;

	@Override
	public void init() throws ServletException {
		service = new ProductOrderServiceImpl();
		dao = new ProductOrderDaoImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		


		resp.getWriter().write("hi");
	}
	
	
}
