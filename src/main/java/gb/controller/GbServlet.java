package gb.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import gb.dao.GBDao;
import gb.dao.GbDAOImpl;
import gb.service.GBService;
import gb.service.GbServiceImpl;
import gb.vo.GbAndProductVO;
import gb.vo.GbVO;
import gb.vo.ProductVO;
import java.io.ByteArrayOutputStream;

@WebServlet("/GbServlet")
public class GbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private GBService service;
	
	
//以下 圖片///////////////////////////////////////////////
	private ServletContext context;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
	    super.init(config);
	    service = new GbServiceImpl();
	    context = config.getServletContext();
	}

//以上。///////////////////////////////////
    

	@Override
	public void init() throws ServletException {
		service = new GbServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Gson gson = new Gson();

		List<GbVO> list = service.getAllGb();
		List<GbAndProductVO> productList = service.getAllGbJoinProduct();


		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("status", "true");

		JsonArray priceArray = new JsonArray();
		for (GbVO gb : list) {
			JsonObject gbObject = new JsonObject();
			gbObject.addProperty("gb_s_price", gb.getGb_s_price());
			gbObject.addProperty("gb_time_start", gb.getGb_time_start().getTime());
			gbObject.addProperty("gb_time_end", gb.getGb_time_end().getTime());

			priceArray.add(gbObject);
		}
		jsonObject.add("price", priceArray);

		// 將 ProductVO 的資料放入 JSON 物件中
		JsonArray productArray = new JsonArray();

		for (GbAndProductVO gbAndProduct : productList) {
			ProductVO product = gbAndProduct.getProductVO();
			JsonObject productObject = new JsonObject();
			productObject.addProperty("p_name", product.getP_name()); // p_name 商品名稱
			productObject.addProperty("p_price", product.getP_price()); // p_price 商品原價

			productObject.addProperty("p_type", product.getP_type()); // p_type 1狗 2貓
			productObject.addProperty("p_class", product.getP_class()); // p_class 1食物 2用品 3玩具

//以下  圖片/////////////////////////////////////////////////////////////////////////////////////////
			
			 // 將圖片轉換為 Base64 編碼
	        if (product.getP_pic_one() != null) {
	            String base64PicOne = convertImageToBase64(product.getP_pic_one(), context);
	            productObject.addProperty("p_pic_one", base64PicOne);
	        } else {
	            productObject.addProperty("p_pic_one", "");
	        }

	        if (product.getP_pic_two() != null) {
	            String base64PicTwo = convertImageToBase64(product.getP_pic_two(), context);
	            productObject.addProperty("p_pic_two", base64PicTwo);
	        } else {
	            productObject.addProperty("p_pic_two", "");
	        }

	        if (product.getP_pic_three() != null) {
	            String base64PicThree = convertImageToBase64(product.getP_pic_three(), context);
	            productObject.addProperty("p_pic_three", base64PicThree);
	        } else {
	            productObject.addProperty("p_pic_three", "");
	        }

//以上。/////////////////////////////////////////////////////////////////////////////////////////
	        
			// 其他 ProductVO 的屬性也可以依此類推加入
			productArray.add(productObject);
		}
		jsonObject.add("productData", productArray);

		// 將 JSON 物件轉換成字串
		String message = gson.toJson(jsonObject);
		response.getWriter().write(message);
	}

//以下  圖片/////////////////////////////////////////////////////
	
	// 將byte[]型別的圖片資料轉換為Base64字串
	private String convertImageToBase64(byte[] imageBytes, ServletContext context) {
	    return Base64.getEncoder().encodeToString(imageBytes);
	}

//以上。//////////////////////////////////////////////////////
}






