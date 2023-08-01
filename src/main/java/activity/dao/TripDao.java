package activity.dao;

import java.util.List;
import activity.vo.Trip;

public interface TripDao {
	
	//呈現所有資料的功能
	List<Trip> selectAll();
	
	//查詢活動編號
	Trip selectById(String t_act_id);
	
	//選取狗狗或貓貓活動種類篩選器
	Trip selectByActType(Integer t_act_type);
	
	//選取最熱門活動篩選器
	List<Trip> selectHotAct();
	
	//選取最新活動篩選器
	List<Trip> selectNewAct();
	
	//選取台北市或新北市地區活動篩選器
	Trip selectActCity(Integer t_act_city);
	
	//舉辦活動->新增活動
	Integer insertAct(Trip trip);
	
	//修改活動內容
	Integer updateAct(Trip trip);
	
	//活動到期下架(針對活動狀態欄位)
	Integer updateActDeadLine(Trip trip);
	
}