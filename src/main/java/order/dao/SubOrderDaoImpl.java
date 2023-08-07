package order.dao;

import org.hibernate.Session;

import core.util.HibernateUtil;
import order.vo.SubOrder;

public class SubOrderDaoImpl implements SubOrderDao {

	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	@Override
	public int insertSubOrder(SubOrder subOrder) {
		getSession().persist(subOrder);
		return subOrder.getSo_order_id();
	}
}
