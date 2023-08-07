package product_fe.controller;

import java.io.IOException;
import java.util.List;

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
import product_fe.service.ProductLikeService;
import product_fe.service.ProductLikeServiceImpl;

@WebServlet("/shop/productDetail/productLike")
public class ProductLikeController extends HttpServlet {
	private ProductLikeService service;
	private MemberDao memberDao;

	@Override
	public void init() throws ServletException {
		service = new ProductLikeServiceImpl();
		memberDao = new MemberDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		Gson gson = new Gson();
		String message = "";
		String username = (String) session.getAttribute("username");

		if (username == null) {
			List<String> msgs = service.getMsgs();
			message = "{\"action\":\"login\",\"msgs\":\"請先登入\"}";
			resp.getWriter().write(message);
			return;

		} else if (username != null) {
			int pl_uid = memberDao.selectByUserNameForCart(username).getUid();
			int pl_p_id = Integer.parseInt(req.getParameter("pl_p_id"));
			boolean productLikeExists = service.manageProductLike(pl_uid, pl_p_id);
			List<String> msgs = service.getMsgs();
			
			if(productLikeExists == false) {
				message = "{\"action\":\"insert\",\"msgs\":" + gson.toJson(msgs) + "}";
				
			} else if(productLikeExists == true) {
				message = "{\"action\":\"delete\",\"msgs\":" + gson.toJson(msgs) + "}";
			}
			
			resp.getWriter().write(message);
			System.out.println(message);
		}

			

			
	}

}
