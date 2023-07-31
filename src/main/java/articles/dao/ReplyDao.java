package articles.dao;

public interface ReplyDao {

	int insertReply(String reply_com_id, String reply_user_id, String reply_content);

}
