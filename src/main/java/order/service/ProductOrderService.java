package order.service;

import java.util.List;

import order.vo.Orders;
import order.vo.ProductOrder;
import order.vo.SubOrder;
import order.vo.SubProduct;

public interface ProductOrderService {
	
	boolean createOrders(Orders orders);
	
	List<String> getMsgs(); 

//	boolean createOrders(ProductOrder productOrder, SubOrder subOrder, SubProduct subProduct); // 測試用
	
}