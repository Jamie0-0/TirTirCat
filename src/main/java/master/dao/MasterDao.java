package master.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import core.util.HibernateUtil;
import master.vo.Master;

public class MasterDao {

	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	public Master insert(Master master) {
		try {
			Transaction tx = getSession().beginTransaction();
			getSession().persist(master);
			tx.commit();
			return master;
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}
}
