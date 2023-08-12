package product_fe.dao;

import java.util.Map;

public interface ProductJedisDao {

	void setCartList(Map<String, String> cartListString, int uid);

	void deleteCartItem(Map<String, String> cartListString, int uid, String p_id);

}