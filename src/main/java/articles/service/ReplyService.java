package articles.service;

import core.CoreService;

public interface ReplyService extends CoreService{

	int insertReply(String reply_com_id, String reply_user_id, String reply_content);

}
