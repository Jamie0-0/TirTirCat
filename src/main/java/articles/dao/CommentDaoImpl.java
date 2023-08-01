package articles.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import articles.vo.Reply;
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
		String hql = "FROM Comment c WHERE c.com_art_id = :com_art_Id";
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

	@Override
	public List<Reply> selectReply(String reply_com_id) {
		
		String hql = "FROM Reply r WHERE r.reply_com_id = :reply_com_id";
		Integer int_reply_com_id = Integer.parseInt(reply_com_id);
		Session session = getSession();

		try {
			Transaction transaction = session.beginTransaction();
			Query<Reply> query = session.createQuery(hql, Reply.class).setParameter("reply_com_id", int_reply_com_id);
			List<Reply> list = query.getResultList();  // 注意這段要放在 transaction.commit();之前 以免session先關閉了			}
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
	public int insertComment(String com_art_id, String com_user_id, String com_content) {

		int status = 0;

		Comment comment =  new Comment();
		
        comment.setCom_art_id(Integer.parseInt(com_art_id)); // 設置相應的屬性值
        comment.setCom_user_id(Integer.parseInt(com_user_id));
        comment.setCom_content(com_content);
        
        getSession().persist(comment);
       
        status = 1;

		return status;
	}
	
	@Override
	public String updateComment(Comment newComment) {

		String status = ""; 
		Comment comment = getSession().load(Comment.class, newComment.getCom_id());
        comment.setCom_content(newComment.getCom_content());
        getSession().persist(comment);
        status ="修改留言成功";
		return status;
	}

	@Override
	public void deleteComment(String com_id) {
		getSession().remove(com_id);
	}

}
