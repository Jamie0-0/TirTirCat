package articles.service;

import java.util.List;

import articles.vo.Comment;
import articles.vo.Reply;

public interface CommentService {

	List<Comment> selectComment(String com_art_id);

	List<Reply> selectReply(String reply_com_id);


}