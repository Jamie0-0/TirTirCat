package articles.dao;

import java.util.List;

import articles.vo.Comment;

public interface CommentDao {
	
	

	List<Comment> selectComRedis(String com_art_id);

	List<Comment> selectComById(String com_art_id);

	void saveComToRedis(List<Comment> list);

}
