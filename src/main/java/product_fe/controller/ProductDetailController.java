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
import product_fe.service.ProductLikeService;
import product_fe.service.ProductLikeServiceImpl;
import product_fe.service.ProductService;
import product_fe.service.ProductServiceImpl;

@WebServlet("/shop/productDetail")
public class ProductDetailController extends HttpServlet {

	private ProductService service;
	private ProductLikeService plService;
	private MemberDao memberDao;

	@Override
	public void init() throws ServletException {
		service = new ProductServiceImpl();
		plService = new ProductLikeServiceImpl();
		memberDao = new MemberDaoImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();

		int p_id = Integer.parseInt(req.getParameter("p_id"));
		
		boolean productLikeExists = false;

		Gson gson = new Gson();
		var product = service.viewProductDetail(p_id);

		if (session.getAttribute("username") != null) {
			String username = (String) session.getAttribute("username");
			int pl_uid = memberDao.selectByUserNameForCart(username).getUid();
			productLikeExists = plService.showProductLike(pl_uid, p_id);
			List<String> msgs = service.getMsgs();
		}

		String message = gson.toJson("");

		message = "{\"productLike\":" + productLikeExists + ",\"viewProductDetail\":" + gson.toJson(product) + "}";
		resp.getWriter().write(message);

		System.out.println(message);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
