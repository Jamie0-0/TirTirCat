package articles.dao;

import java.util.List;

import articles.vo.Comment;
import articles.vo.Reply;

public interface CommentDao {
	
	

	List<Comment> selectComRedis(String com_art_id);

	List<Comment> selectComById(String com_art_id);

	void saveComToRedis(List<Comment> list);

	List<Reply> selectReply(String reply_com_id);

	String insertComment(String com_art_id, String com_user_id, String com_content);

	String updateComment(Comment newComment);

	void deleteComment(String com_id);

}
