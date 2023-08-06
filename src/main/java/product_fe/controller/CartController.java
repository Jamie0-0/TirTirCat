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

import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import member.dao.MemberDao;
import member.dao.MemberDaoImpl;
import member.vo.Member;
import product_fe.service.ProductService;
import product_fe.service.ProductServiceImpl;
import product_fe.vo.Product;

@WebServlet("/cartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductService service;

	@Override
	public void init() throws ServletException {
		service = new ProductServiceImpl();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Gson gson = new Gson();

		HashMap<Integer, Integer> cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");

		JsonArray cartArray = service.getCartListJSON(cartList);
		// 若購物車是空的, 傳給前端訊息並結束
		List<String> msgs = service.getMsgs();
		if (!msgs.isEmpty()) {
			resp.getWriter().write(gson.toJson(msgs));
			System.out.println(msgs);
			System.out.println(gson.toJson(msgs));
			return;
		}

		int subtotal = service.getCartSubTotal(cartList);

		// 用會員名稱查詢電話住址(放到service裡?)
		MemberDao dao = new MemberDaoImpl();
//		String username = (String) session.getAttribute("username");
//		Member member = dao.selectByUserName(username);
		Member member = dao.selectByUserNameForCart("Jane"); // 測試寫死

		String message = "{\"user\":" + gson.toJson(member) + ",\"cartList\":" + gson.toJson(cartArray)
				+ ",\"subtotal\":" + subtotal + ",\"total\":" + (subtotal + 120) + "}";
		resp.getWriter().write(message);
		System.out.println(message);

//		String message = service.getCartListAndTotalJSON(cartList);
//		System.out.println("cartListJSON =" + cartListJSON);  // cartListJSON ={"2":5}
//		System.out.println("getCartListAndTotalJSON =" + message); // {"cartList":[{"p_id":2,"p_name":"商品2潔牙骨","p_price":150,"quantity":5}],"subtotal":750,"total":870}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

}
