package product_fe.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;

import product_fe.vo.Product;

public interface ProductService {
	
	List<Product> findAllForShopList();
	
	List<Product> selectByKeyWords(String how, String keywords); 
		
	Product viewProductDetail(Integer p_id);
	
	List<String> getMsgs(); 
	
	void addToCart(HttpServletRequest req, String p_id, String quantity, Map<Integer, Integer> cartList);
	
	Map<Product, Integer> getCartList(Map<Integer, Integer> cartList);
	
	void updateCart(HttpServletRequest req, int p_id, int quantity, Map<Integer, Integer> cartList);
	
	boolean deleteProductInCart(HttpServletRequest req, int p_id, Map<Integer, Integer> cartList);
	
	int getCartSubTotal(Map<Integer, Integer> cartList);
	
	JsonArray getCartListJSON(Map<Integer, Integer> cartList); //cartcontroller用這個?
	
	void saveCartToRedis(HttpSession session, int uid);
	
	void deleteCartItemFromRedis(HttpSession session, int uid, int p_id);
	
//	String getCartListAndTotalJSON(Map<Integer, Integer> cartList); //cartcontroller用這個?
}
