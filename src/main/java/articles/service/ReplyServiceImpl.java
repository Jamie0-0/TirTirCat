package articles.service;

import articles.dao.ReplyDao;
import articles.dao.ReplyDaoImpl;

public class ReplyServiceImpl implements ReplyService {
	
	private ReplyDao dao;

	public ReplyServiceImpl() {
		dao = new ReplyDaoImpl();
	}

	@Override
	public int insertReply(String reply_com_id, String reply_user_id, String reply_content) {
		
		int status = dao.insertReply(reply_com_id, reply_user_id,  reply_content);

		return status;
	}
	
	
}
