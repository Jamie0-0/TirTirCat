package articles.service;

import java.util.List;

import articles.vo.Comment;

public interface CommentService {

	List<Comment> selectComment(String com_art_id);


}