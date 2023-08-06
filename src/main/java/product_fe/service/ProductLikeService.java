package product_fe.service;

import java.util.List;

public interface ProductLikeService {
	
	boolean manageProductLike(int pl_uid, int pl_p_id);
	
	boolean showProductLike(int pl_uid, int pl_p_id);
	
	List<String> getMsgs(); 
}