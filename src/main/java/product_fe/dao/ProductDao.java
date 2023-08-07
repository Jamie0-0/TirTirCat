package product_fe.dao;

import java.util.List;
import java.util.Set;

import product_fe.vo.Product;

public interface ProductDao {

	List<Product> selectAll(); // 測試用

	List<Product> selectForShop(); // 商城首頁

	List<Product> selectByPType(String p_type); // 選狗貓商品

	List<Product> selectByPClass(String p_class); // 選玩具, 食物, 用品

//	List<Product> selectByPTypeAndPClass(String p_type, String p_class); // 同時選寵物及商品類別 還是用service一次呼叫上面兩個?

	int selectPStockByPid(Integer p_id); // 購買數量不可超過庫存, 否則無法加入+顯示錯誤訊息

	Product selectByPId(Integer p_id); // 商品詳細頁面用

	List<Product> search(String inputText); // 關鍵字搜尋

	byte[] selectPicByPid(Integer p_id);

	int updatePStockByPid(Integer p_id, Integer quantity); // 結帳 -> 更新廠商商品數量

}
