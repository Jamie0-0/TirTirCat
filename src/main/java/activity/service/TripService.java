package activity.service;

import java.util.List;

import activity.vo.Trip;

public interface TripService { 

	//寫商業邏輯
	
	//show所有活動
	public List<Trip> showAllAct();
	
	//show狗或貓的活動篩選器
	public Trip showByActType(Integer t_act_type);
	
	//show熱門活動篩選器
	public List<Trip> showHotAct();
	
	//show最新活動篩選器
	public List<Trip> showNewAct();
	
	//show活動地區篩選器(台北市或新北市)
	public Trip showActCity(Integer t_act_city);
	
	//舉辦活動
	public Trip hostEvent(Trip trip);
	 
    public Trip processUserInput(int inputLength);
       
}