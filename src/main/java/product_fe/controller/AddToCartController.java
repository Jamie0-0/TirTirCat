package product_fe.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import product_fe.service.ProductService;
import product_fe.service.ProductServiceImpl;
import product_fe.vo.Product;

@WebServlet("/addToCart")
public class AddToCartController extends HttpServlet {

	private ProductService service;

	@Override
	public void init() throws ServletException {
		service = new ProductServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 判斷下單數量是否超過商品庫存數量, 若超過則回傳錯誤訊息, 結束方法
		// 2. 判斷session中是否已有購物車, 沒有的話先new再put
		// 3. 新增至購物車後, 回傳訊息至前端燈箱訊息
		// 4. 判斷是否登入??????? 登入的話存入redis?

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Gson gson = new Gson();

		String p_id_string = req.getParameter("p_id");
		String quantity_string = req.getParameter("quantity");

		HashMap<Integer, Integer> cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");

		System.out.println("add之前的原本的CartList =" + cartList);
		service.addToCart(req, p_id_string, quantity_string, cartList);

		// 檢查欲增加數量有沒有大於商品庫存數量, 有的話直接回傳錯誤訊息就結束
		List<String> msgs = service.getMsgs();
		if (!msgs.isEmpty()) {
			resp.getWriter().write(gson.toJson(msgs));
			return;
		}

		msgs.add("已成功加入購物車");
		resp.getWriter().write(gson.toJson(msgs)); // 回傳成功文字

		String newCartList = gson.toJson(session.getAttribute("cartList"));

		String message = "{\"msgs\":" + gson.toJson(msgs) + ",\"cartlist\":" + gson.toJson(newCartList) + "}";
		System.out.println(message);

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
