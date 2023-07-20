package articles.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import articles.vo.Comment;
import core.util.HibernateUtil;

public class CommentDaoImpl implements CommentDao {

	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@Override
	public List<Comment> selectComRedis(String com_art_id) {

		return null;
	}

	@Override
	public List<Comment> selectComById(String com_art_id) {
		String hql = "SELECT c FROM Comment c WHERE c.com_art_id = :com_art_Id";
		Integer int_com_art_id = Integer.parseInt(com_art_id);
		Session session = getSession();

		try {
			Transaction transaction = session.beginTransaction();
			Query<Comment> query = session.createQuery(hql, Comment.class).setParameter("com_art_Id", int_com_art_id);
			List<Comment> list = query.getResultList();  // 注意這段要放在 transaction.commit();之前 以免session先關閉了
			transaction.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		HibernateUtil.shutdown();

		return null;
	}

	@Override
	public void saveComToRedis(List<Comment> list) {

	}

}
