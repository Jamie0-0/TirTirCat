package core;

import org.hibernate.Transaction;

import core.util.HibernateUtil;

public interface CoreService {
	
	default Transaction beginTransaction() {
		return HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
	};
	
	default void commit() {
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
	}
	
	default void rollback() {
		HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
	}
	
	

}
