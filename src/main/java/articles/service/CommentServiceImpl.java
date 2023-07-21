package articles.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import articles.dao.CommentDao;
import articles.dao.CommentDaoImpl;
import articles.vo.Comment;
import articles.vo.Reply;
import core.util.HibernateUtil;

public class CommentServiceImpl implements CommentService {

	private CommentDao dao;

	public CommentServiceImpl() {
		dao = new CommentDaoImpl();
	}

	@Override
	public List<Comment> selectComment(String com_art_id) {

		List<Comment> list;

		
//		if (dao.selectComRedis(com_art_id) ==null || dao.selectComRedis(com_art_id).isEmpty()) {
			list = dao.selectComById(com_art_id);
//			dao.saveComToRedis(list); // 把熱門全部存進去
//		} else {
//			list = dao.selectComRedis(com_art_id); // 調用部分熱門
//		}

		return list;

	}

	@Override
	public List<Reply> selectReply(String reply_com_id) {
		List<Reply> list;
			list = dao.selectReply(reply_com_id);

		return list;
	}

}
