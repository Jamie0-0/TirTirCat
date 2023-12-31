package articles.service;

import java.util.List;

import articles.vo.Comment;
import articles.vo.Reply;
import core.CoreService;

public interface CommentService extends CoreService{

	List<Comment> selectComment(String com_art_id);

	List<Reply> selectReply(String reply_com_id);

	int insertComment(String com_art_id, String com_user_id, String com_content);
	
	boolean deleteComment(String com_id);

	String updateComment(String com_id, String com_content);


}