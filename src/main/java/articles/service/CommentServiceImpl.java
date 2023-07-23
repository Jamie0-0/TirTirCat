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

	@Override
	public String insertComment(String com_art_id, String com_user_id, String com_content) {
		String status = "";
		try {
			beginTransaction();
			status = dao.insertComment(com_art_id, com_user_id, com_content);
			commit();
			System.out.println("新增留言成功");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			status = "新增留言失敗";
		}
		return status;
	}

	
	@Override
	public boolean deleteComment(String com_id) {
		Boolean status = false;
		try {
		beginTransaction();
		dao.deleteComment(com_id);
		commit();
		status = true;
		System.out.println("deleteComment 成功與否:"+status);
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			System.out.println("deleteComment 成功與否:"+status);
		}
		return status;
 	}

	@Override
	public String updateComment(String com_id, String com_content) {
		String status ="";
		try {
		beginTransaction();
		Comment newComment = new Comment();
		newComment.setCom_id(Integer.parseInt(com_id));
		newComment.setCom_content(com_content);
		status = dao.updateComment(newComment);
	    commit();
	    System.out.println(status);
		} catch (Exception e) {
			e.printStackTrace();
			status = "修改留言失敗";
			System.out.println(status);
		}
		
		return status;
	}

}
