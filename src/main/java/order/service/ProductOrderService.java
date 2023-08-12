package order.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import order.vo.Orders;

public interface ProductOrderService {
	
	boolean createOrders(Orders orders);
	
	List<String> getMsgs(); 
	
	void deleteCartFromRedis(HttpSession session);

//	boolean createOrders(ProductOrder productOrder, SubOrder subOrder, SubProduct subProduct); // 測試用
	
}