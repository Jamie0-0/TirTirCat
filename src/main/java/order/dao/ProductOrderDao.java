package order.dao;

import java.util.List;

import order.vo.ProductOrder;

public interface ProductOrderDao {

	boolean insert(ProductOrder productOrder);
	
	void deleteByOrderId(String order_id);
	
	boolean update(ProductOrder productOrder);
		
//	List<ProductOrder> selectAll();
	
//	List<ProductOrder> selectByUid(String order_uid); // 會員查自己所有訂單 -> join子訂單+子訂單明細?

}
