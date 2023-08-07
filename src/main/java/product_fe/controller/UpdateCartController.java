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

import member.dao.MemberDao;
import member.dao.MemberDaoImpl;
import member.vo.Member;
import product_fe.service.ProductService;
import product_fe.service.ProductServiceImpl;
import product_fe.util.ProductUtil;
import redis.clients.jedis.Jedis;
import webSocket.jedis.JedisPoolUtil;

@WebServlet("/updateCart")
public class UpdateCartController extends HttpServlet {

	private ProductService service;
	private MemberDao memberDao;

	@Override
	public void init() throws ServletException {
		service = new ProductServiceImpl();
		memberDao = new MemberDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Gson gson = new Gson();
		
		//////
		String username = (String) session.getAttribute("username");
		Member member = new Member();
		int uid = 0;
		
		HashMap<Integer, Integer> cartList = null;
		
		if (username == null) {
			cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");
			System.out.println("用到1");
		} else if (username != null) {

			System.out.println("用到2");
			System.out.println("username = "+username);
			member =  memberDao.selectByUserNameForCart(username);
			uid = member.getUid();

			Jedis jedis = JedisPoolUtil.getJedisPool().getResource();
			Map<String, String> reddisCartList = jedis.hgetAll("user:" + uid + ":cart.list");
			
			if(reddisCartList.isEmpty()) {
				cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");
				System.out.println("用到reddisCartList.isEmpty()");
			}else if(!reddisCartList.isEmpty()) {
				cartList = ProductUtil.mapStringCastToInt(reddisCartList);
				System.out.println("用到!reddisCartList.isEmpty()");
			}
			jedis.close();
		}
		/////
		

		int p_id = Integer.parseInt(req.getParameter("p_id"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		System.out.println("update的p_id = " + p_id);
		System.out.println("update的quantity = " + quantity);

//		HashMap<Integer, Integer> cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");

		System.out.println("update之前的原本的CartList =" + cartList);
		service.updateCart(req, p_id, quantity, cartList);
		
		if (username != null) {
			service.saveCartToReddis(session, uid);
		}

				

		// 檢查欲增加數量有沒有大於商品庫存數量, 有的話直接回傳錯誤訊息就結束
		List<String> msgs = service.getMsgs();
		if (!msgs.isEmpty()) {
			resp.getWriter().write(gson.toJson(msgs));
			return;
		}

		int subtotal = service.getCartSubTotal(cartList);

		String newCartList = gson.toJson(session.getAttribute("cartList"));
		
		String message = "{\"status\":\"true\",\"cartlist\":" + gson.toJson(newCartList) +
				",\"subtotal\":" + subtotal +",\"total\":" + (subtotal + 120) + "}"; // 運費固定120
		
		resp.getWriter().write(message);
		
		System.out.println(message);
		
		System.out.println("====== 購物車數量已update ========");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
