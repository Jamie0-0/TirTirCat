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
import com.google.gson.JsonArray;

import member.dao.MemberDao;
import member.dao.MemberDaoImpl;
import member.vo.Member;
import product_fe.service.ProductService;
import product_fe.service.ProductServiceImpl;
import product_fe.util.ProductUtil;
import redis.clients.jedis.Jedis;
import webSocket.jedis.JedisPoolUtil;

@WebServlet("/cartController")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductService service;
	private MemberDao memberDao;

	@Override
	public void init() throws ServletException {
		service = new ProductServiceImpl();
		memberDao = new MemberDaoImpl();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		Gson gson = new Gson();

		
		String username = (String) session.getAttribute("username");
		Member member = new Member();
		
		HashMap<Integer, Integer> cartList = null;
		
		if (username == null) {
			cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");
			System.out.println("user =null, cartList = " +cartList);
			System.out.println("用到1");

		} else if (username != null) {
			System.out.println("用到2");
			System.out.println("username = "+username);
			cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");
			
			member =  memberDao.selectByUserNameForCart(username);
			int uid = member.getUid();

			Jedis jedis = JedisPoolUtil.getJedisPool().getResource();
			Map<String, String> reddisCartList = jedis.hgetAll("user:" + uid + ":cart.list");
			System.out.println("reddisCartList=" + reddisCartList);
			
			
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

//	HashMap<Integer, Integer> cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");

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
//	MemberDao dao = new MemberDaoImpl();
//	String username = (String) session.getAttribute("username");
//	Member member = memberDao.selectByUserNameForCart(username);
//	Member member = dao.selectByUserNameForCart("Jane"); // 測試寫死

	String message = "{\"user\":" + gson.toJson(member) + ",\"cartList\":" + gson.toJson(cartArray)
			+ ",\"subtotal\":" + subtotal + ",\"total\":" + (subtotal + 120) + "}";
	resp.getWriter().write(message);
	System.out.println(message);

//	String message = service.getCartListAndTotalJSON(cartList);
//	System.out.println("cartListJSON =" + cartListJSON);  // cartListJSON ={"2":5}
//	System.out.println("getCartListAndTotalJSON =" + message); // {"cartList":[{"p_id":2,"p_name":"商品2潔牙骨","p_price":150,"quantity":5}],"subtotal":750,"total":870}
	
		
		//////////////////////////////////////////////
	//////
//			String username = (String) session.getAttribute("username");
//			Member member = new Member();
//			
//			HashMap<Integer, Integer> cartList = null;
//			
//			if (username == null) {
//				cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");
//				System.out.println("user =null, cartList = " +cartList);
//
//			} else if (username != null) {
//				member =  memberDao.selectByUserNameForCart(username);
//				int uid = member.getUid();
//
//				Jedis jedis = JedisPoolUtil.getJedisPool().getResource();
//				Map<String, String> reddisCartList = jedis.hgetAll("user:" + uid + ":cart.list");
//				System.out.println("reddisCartList=" + reddisCartList);
//				
//				
//				if(reddisCartList == null) {
//					cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");
//				}else if(reddisCartList != null) {
//					cartList = ProductUtil.mapStringCastToInt(reddisCartList);
//				}
//				jedis.close();
//			}
//			/////
//
////		HashMap<Integer, Integer> cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");
//
//		JsonArray cartArray = service.getCartListJSON(cartList);
//		// 若購物車是空的, 傳給前端訊息並結束
//		List<String> msgs = service.getMsgs();
//		if (!msgs.isEmpty()) {
//			resp.getWriter().write(gson.toJson(msgs));
//			System.out.println(msgs);
//			System.out.println(gson.toJson(msgs));
//			return;
//		}
//
//		int subtotal = service.getCartSubTotal(cartList);
//
//		// 用會員名稱查詢電話住址(放到service裡?)
////		MemberDao dao = new MemberDaoImpl();
////		String username = (String) session.getAttribute("username");
////		Member member = memberDao.selectByUserNameForCart(username);
////		Member member = dao.selectByUserNameForCart("Jane"); // 測試寫死
//
//		String message = "{\"user\":" + gson.toJson(member) + ",\"cartList\":" + gson.toJson(cartArray)
//				+ ",\"subtotal\":" + subtotal + ",\"total\":" + (subtotal + 120) + "}";
//		resp.getWriter().write(message);
//		System.out.println(message);
//
////		String message = service.getCartListAndTotalJSON(cartList);
////		System.out.println("cartListJSON =" + cartListJSON);  // cartListJSON ={"2":5}
////		System.out.println("getCartListAndTotalJSON =" + message); // {"cartList":[{"p_id":2,"p_name":"商品2潔牙骨","p_price":150,"quantity":5}],"subtotal":750,"total":870}
//		
		
		/////////////////////////////////////
		
		
		
		
//		resp.setContentType("application/json");
//		resp.setCharacterEncoding("UTF-8");
//		req.setCharacterEncoding("UTF-8");
//
//		HttpSession session = req.getSession();
//		Gson gson = new Gson();
//
//		HashMap<Integer, Integer> cartList = (HashMap<Integer, Integer>) session.getAttribute("cartList");
//
//		JsonArray cartArray = service.getCartListJSON(cartList);
//		// 若購物車是空的, 傳給前端訊息並結束
//		List<String> msgs = service.getMsgs();
//		if (!msgs.isEmpty()) {
//			resp.getWriter().write(gson.toJson(msgs));
//			System.out.println(msgs);
//			System.out.println(gson.toJson(msgs));
//			return;
//		}
//
//		int subtotal = service.getCartSubTotal(cartList);
//
//		// 用會員名稱查詢電話住址(放到service裡?)
//		MemberDao dao = new MemberDaoImpl();
////		String username = (String) session.getAttribute("username");
////		Member member = dao.selectByUserName(username);
//		Member member = dao.selectByUserNameForCart("Jane"); // 測試寫死
//
//		String message = "{\"user\":" + gson.toJson(member) + ",\"cartList\":" + gson.toJson(cartArray)
//				+ ",\"subtotal\":" + subtotal + ",\"total\":" + (subtotal + 120) + "}";
//		resp.getWriter().write(message);
//		System.out.println(message);
//
////		String message = service.getCartListAndTotalJSON(cartList);
////		System.out.println("cartListJSON =" + cartListJSON);  // cartListJSON ={"2":5}
////		System.out.println("getCartListAndTotalJSON =" + message); // {"cartList":[{"p_id":2,"p_name":"商品2潔牙骨","p_price":150,"quantity":5}],"subtotal":750,"total":870}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

}
