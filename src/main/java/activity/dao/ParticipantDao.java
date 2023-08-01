package activity.dao;

import activity.vo.Participant;
import activity.vo.Trip;

public interface ParticipantDao {

	//顯示活動參加人數目前有多少
	Integer selectActJoin(Integer t_act_id, String uid_join);
	
	//click事件後，帶著活動id&會員id新增到列表中
	//imIn：參加活動
	Integer imInAct(Participant participant);
}