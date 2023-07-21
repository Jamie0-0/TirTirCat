package articles.service;

import java.util.List;

import articles.dao.CommentDao;
import articles.dao.CommentDaoImpl;
import articles.vo.Comment;
import articles.vo.Reply;
import redis.clients.jedis.exceptions.JedisException;

public class CommentServiceImpl implements CommentService {

	private CommentDao dao;

	public CommentServiceImpl() {
		dao = new CommentDaoImpl();
	}

	@Override
	public List<Comment> selectComment(String com_art_id) {

		List<Comment> list = null;

		try {
			dao.selectComRedis(com_art_id);
			if (dao.selectComRedis(com_art_id) == null || dao.selectComRedis(com_art_id).isEmpty()) {
				list = dao.selectComById(com_art_id);
				dao.saveComToRedis(list); 
			}
		} catch (JedisException e) {
			System.out.println("selectComRedis錯誤");
			e.printStackTrace();
			list = dao.selectComById(com_art_id);
		} catch (Exception e) {
			System.out.println("selectComment其他錯誤");
		}

		return list;

	}

	@Override
	public List<Reply> selectReply(String reply_com_id) {
		List<Reply> list;
		list = dao.selectReply(reply_com_id);

		return list;
	}

}
