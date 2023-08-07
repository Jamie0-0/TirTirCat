package product_fe.service;

import java.util.List;

public interface ProductLikeService {

//	List<String> manageProductLike(int pl_uid, int pl_p_id);
	
	boolean manageProductLike(int pl_uid, int pl_p_id);
	
//	String manageProductLike(String pl_uid, String pl_p_id);
	
	boolean showProductLike(int pl_uid, int pl_p_id);
	
	List<String> getMsgs(); 
}