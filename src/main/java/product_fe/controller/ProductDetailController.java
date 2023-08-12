package product_fe.controller;

import java.io.IOException;
import java.util.Base64;
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
		String base64PicOne ="";
		String base64PicTwo ="";
		String base64PicThree ="";
		String base64PicFour ="";

		Gson gson = new Gson();
		var product = service.viewProductDetail(p_id);
		
		 // 將圖片轉換為 Base64 編碼
        if (product.getP_pic_one() != null) {
            base64PicOne = Base64.getEncoder().encodeToString(product.getP_pic_one());
        } else {
        	base64PicOne = "";
        }

        if (product.getP_pic_two() != null) {
        	 base64PicTwo = Base64.getEncoder().encodeToString(product.getP_pic_two());
        } else {
        	 base64PicTwo = "";
        }

        if (product.getP_pic_three() != null) {
        	base64PicThree = Base64.getEncoder().encodeToString(product.getP_pic_three());
        } else {
        	base64PicThree = "";
        }
        
        if (product.getP_pic_four() != null) {
        	base64PicFour = Base64.getEncoder().encodeToString(product.getP_pic_four());
        } else {
        	base64PicFour = "";
        }
		
		
        // 商品追蹤是否有登入判斷
		if (session.getAttribute("username") != null) {
			String username = (String) session.getAttribute("username");
			int pl_uid = memberDao.selectByUserNameForCart(username).getUid();
			productLikeExists = plService.showProductLike(pl_uid, p_id);
			List<String> msgs = service.getMsgs();
		}

		String message = gson.toJson("");

		message = "{\"productLike\":" + productLikeExists + ",\"viewProductDetail\":" + gson.toJson(product) + 
				",\"picOne\":" + gson.toJson(base64PicOne) + ",\"picTwo\":" + gson.toJson(base64PicTwo) + ",\"picThree\":" + gson.toJson(base64PicThree) + ",\"picFour\":" + gson.toJson(base64PicFour) + "}";
		resp.getWriter().write(message);

		System.out.println(message);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
