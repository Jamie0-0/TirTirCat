package order.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import order.service.ProductOrderService;
import order.service.ProductOrderServiceImpl;
import order.vo.Orders;

@WebServlet("/checkoutCart")
public class CheckoutCartController extends HttpServlet {

	private ProductOrderService service;

	@Override
	public void init() throws ServletException {
		service = new ProductOrderServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		Gson gson = new Gson();
		String message = gson.toJson("");
		// 1. ok 清空購物車
		// 2. ok 生成母訂單, 子訂單, 訂單明細 (訂單狀態: 預設未出貨?)
		// 3. ok 商品數量跟著購買數量減少
		// 4. ok 燈箱出現下單成功之後要跳轉去商城首頁
		// 5. 宅配資訊格式錯誤訊息?

		Orders orders = gson.fromJson(req.getReader(), Orders.class);
		boolean orderIsEstabished = service.createOrders(orders);

		if (orderIsEstabished == true) {
			// session版 購物車清空
			HashMap<Integer, Integer> cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");
			cartList.clear();

//			message = "{\"status\":\"true\",\"msgs\":" + gson.toJson(service.getMsgs()) + "}";
			resp.getWriter().write(gson.toJson(service.getMsgs()));
		} else if (orderIsEstabished == false) {
//			message = "{\"status\":\"false\",\"msgs\":" + gson.toJson(service.getMsgs()) + "}";
			resp.getWriter().write(gson.toJson(service.getMsgs()));
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
