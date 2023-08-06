package order.dao;

import org.hibernate.Session;

import core.util.HibernateUtil;
import order.vo.SubProduct;

public class SubProductDaoImpl implements SubProductDao {
	
	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	@Override
	public int insertSubProduct(SubProduct subProduct) {
		getSession().persist(subProduct);
		return 0;
	}
}
