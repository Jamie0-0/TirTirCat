package master.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
	
	public List<Master> selectAll() {
		try {
			Transaction tx = getSession().beginTransaction();
			final String hql = "FROM Master ORDER BY m_id";
			List<Master> result = getSession().createQuery(hql, Master.class).getResultList();
			tx.commit();
			return result;
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}
	
	public Master selectByMgui(String mGui) {
		try {
			Transaction tx = getSession().beginTransaction();
			CriteriaBuilder cBuilder = getSession().getCriteriaBuilder();
			CriteriaQuery<Master> cQuery = cBuilder.createQuery(Master.class);
			Root<Master> root = cQuery.from(Master.class);
			cQuery.where(cBuilder.equal(root.get("mGui"), mGui));
			Master result = getSession().createQuery(cQuery).uniqueResult();
			tx.commit();
			return result;
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}
	
	
}
