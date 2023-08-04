package articles.dao;

import org.hibernate.Session;

import articles.vo.Reply;
import core.util.HibernateUtil;

public class ReplyDaoImpl implements ReplyDao {
	
	private Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}

	@Override
	public int insertReply(String reply_com_id, String reply_user_id, String reply_content) {

		int status = 0;

		Reply reply = new Reply();
		
		reply.setReply_com_id(Integer.parseInt(reply_com_id)); // 設置相應的屬性值
		reply.setReply_user_id(Integer.parseInt(reply_user_id));
		reply.setReply_content(reply_content);

		getSession().persist(reply);
		
		HibernateUtil.shutdown();

		status = 1;

		return status;
	}

}
