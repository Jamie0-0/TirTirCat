package product_fe.dao;

import product_fe.vo.ProductLike;

public interface ProductLikeDao {

//	int insert(ProductLike productLike);
//
//	int delete(ProductLike productLike);
	
	int insert(int pl_uid, int pl_p_id);

	int delete(int pl_uid, int pl_p_id);
	
	ProductLike selectByUidAndPId(int pl_uid, int pl_p_id);
	
//	boolean selectByUidAndPId(String pl_uid, String pl_p_id);

}