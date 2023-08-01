package activity.service.impl;

import java.util.List;

import activity.dao.TripDao;
import activity.dao.impl.TripDaoImpl;
import activity.service.TripService;
import activity.vo.Trip;


public class TripServiceImpl implements TripService {

	private TripDao tripdao;
	
	public TripServiceImpl() {
	 tripdao = new TripDaoImpl();
	}
	
	//show所有活動
	@Override
	public List<Trip> showAllAct() {
		System.out.println("showAllAct"); //執行此方法()
		return tripdao.selectAll();
	}
	
	//show狗或貓的活動篩選器
	@Override
	public Trip showByActType(Integer t_act_type) {
		System.out.println("showByActType");
		if (t_act_type == 1) {
			System.out.println("狗");
		} else if(t_act_type == 2) {
			System.out.println("貓");
		}
		return tripdao.selectByActType(t_act_type);
	}
	
	//show熱門活動篩選器
	@Override
	public List<Trip> showHotAct() {
		System.out.println("showHotAct"); //執行此方法()
		return tripdao.selectHotAct();
		
	}
	
	//show最新活動篩選器
	@Override
	public List<Trip> showNewAct() {
		System.out.println("showNewAct"); //執行此方法()
		return tripdao.selectNewAct();
	}
	
	//show活動地區篩選器(台北市或新北市)
	@Override
	public Trip showActCity(Integer t_act_city) {
		System.out.println("showActCity");
		if (t_act_city == 1) {
			System.out.println("taipei");
		} else if (t_act_city == 2) {
			System.out.println("newtaipei");
		}
		return tripdao.selectActCity(t_act_city);
	}
	//舉辦活動
	@Override
	//所在城市、活動時間、人數限制判斷
	public Trip hostEvent(Trip trip) {		

		return trip;
	}
	//文字長度,數字,符號限制判斷(活動名稱、活動描述、活動地點、活動預算)
	@Override
	public Trip processUserInput(int inputLength) {
		
		return null;
	}
}
 