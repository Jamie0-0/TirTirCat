package activity.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.mysql.cj.x.protobuf.MysqlxCrud.Insert;

import activity.dao.TripDao;
import activity.vo.Trip;

public class TripDaoImpl implements TripDao{
	//連線持目前有問題(20行)
	private static DataSource ds;
	
	//彬華JDBC寫死的DriverManager寫法(23-25行)
	private final static String url="jdbc:mysql://localhost:3306/Furrever?serverTimezone=Asia/Taipei";
	private final static String user="root";
	private final static String password="password";
	
	//連線持目前有問題(28-34行)
	static {
		try {
			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FurrEver");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	//測試當前程式用
	public static void main(String[] args) {
		
		//show所有資料的功能測試
//		List<Trip> ll = new TripDaoImpl().selectAll();
//		for(Trip tp : ll) {
//			System.out.println(tp);
//		}
		//show活動種類(狗或貓)篩選器測試，1是狗、2是貓
//		Trip tt = new TripDaoImpl().selectByActType(2);
//        	System.out.println(tt.toString());   	
		//show最熱門活動篩選器測試
//    	List<Trip> ll = new TripDaoImpl().selectHotAct();
//    	for(Trip tp : ll) {
//    		System.out.println(tp);
//    	}
		//show最新活動篩選器測試
//    	List<Trip> ll = new TripDaoImpl().selectNewAct();
//    	for(Trip tp : ll) {
//    		System.out.println(tp);
//    	}
        //show活動地區(新北市或台北市)篩選器測試，1是taipei、2是newtaipei
//      Trip tt = new TripDaoImpl().selectByActCity(2);
//        	System.out.println(tt.toString()); 
		
		//新增活動測試
        String sql = "INSERT INTO trip (uid, t_act_type, t_act_name, t_act_desc, t_act_city, t_act_loc, t_act_time, t_act_ppl, t_act_pic_one, t_act_pic_two, t_act_bdg, t_act_status)\n"
        		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ? ,? ,?, ?, ?);";		
        Trip tt =new Trip(); 
        
        tt.setUid(1);
        tt.setT_act_type(2);
        tt.setT_act_name("Pet Food Adventure");
        tt.setT_act_desc("Get ready to embark on a one-of-a-kind Pet Restaurant Adventure, where delectable treats and heartwarming moments with our furry companions await you.");
        tt.setT_act_city(2);
        tt.setT_act_loc("newtaipei");
        tt.setT_act_time("2023-08-29 18:00:00");
        tt.setT_act_ppl(40);
        tt.setT_act_pic_one(null);
        tt.setT_act_pic_two(null);
        tt.setT_act_bdg(2500); 
        tt.setT_act_status("1");

        Integer ii = new TripDaoImpl().insertAct(tt);
	    System.out.println(ii); 
		
		//修改活動測試
// 		String sql = "update trip "
//		+ "set t_act_type = ?, t_act_name = ?, t_act_desc = ?, t_act_city = ?, t_act_loc = ?, t_act_time = ?, t_act_ppl = ?,
//		t_act_pic_one = ?, t_act_pic_two = ?, t_act_bdg = ?, t_act_status = ?"
//		+ "where t_act_id = ?;";
//		Trip tt =new Trip();
//		
//		tt.setT_act_type(2);
//		tt.setT_act_name("jason");
//		tt.setT_act_desc("try try");
//		tt.setT_act_city(1);
//		tt.setT_act_loc("taipei");
//		tt.setT_act_time("2023-06-25 11:00:00");
//		tt.setT_act_ppl(10);
//		tt.setT_act_pic_one(null);
//		tt.setT_act_pic_two(null);
//		tt.setT_act_bdg(1000);
//		tt.setT_act_status("1");
//		tt.setT_act_id(4);
//		
//		Integer ll =new TripDaoImpl().updateAct(tt);
		
		//活動到期下架測試，顯示0表示隱藏、1
//		Trip tt =new Trip();
//		
//	    tt.setT_act_status("1");
//		tt.setT_act_id(5);
//		
//		Integer ll =new TripDaoImpl().updateActDeadLine(tt);
//		
//		System.out.println(ll);
		
	}
	
	//使用Servlet，需將DriverManager改為Data Source，調整如下
	//-> Connection conn = ds.getConnection();
	//push到git時記得把main方法整個拿掉呦！
	
	//呈現所有資料的功能
	@Override
	public List<Trip> selectAll() {
		final String sql = "select * from FurrEver.trip";
		try (
				Connection conn = DriverManager.getConnection(url,user,password);
//				Connection conn =  ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
				List<Trip> list = new ArrayList<>();
				while (rs.next()) {
					Trip trip = new Trip();
					trip.setT_act_id(rs.getInt("t_act_id"));
					trip.setUid(rs.getInt("uid"));
					trip.setT_act_type(rs.getInt("t_act_type"));
					trip.setT_act_name(rs.getString("t_act_name"));
					trip.setT_act_desc(rs.getString("t_act_desc"));
					trip.setT_act_city(rs.getInt("t_act_city"));
					trip.setT_act_loc(rs.getString("t_act_loc"));
//					trip.setT_act_time(rs.getTimestamp("t_act_time"));
					trip.setT_act_time(rs.getString("t_act_time"));
					trip.setT_act_ppl(rs.getInt("t_act_ppl"));
					trip.setT_act_pic_one(rs.getBytes("t_act_pic_one"));
					trip.setT_act_pic_two(rs.getBytes("t_act_pic_two"));
					trip.setT_act_bdg(rs.getInt("t_act_bdg"));
					trip.setT_act_status(rs.getString("t_act_status"));				

					list.add(trip);
				}
				System.out.println("selectAll");
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	
	//查詢活動編號(參加鍵按下去前)
	@Override
	public Trip selectById(String t_act_id) {
		final String sql = "select * from FurrEver.trip where t_act_id = ?";
		try (
				Connection conn = DriverManager.getConnection(url,user,password);
//				Connection conn =  ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, 1);
				try (
					ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						Trip trip = new Trip();
						trip.setT_act_id(rs.getInt("t_act_id"));
						trip.setUid(rs.getInt("uid"));
						trip.setT_act_type(rs.getInt("t_act_type"));
						trip.setT_act_name(rs.getString("t_act_name"));
						trip.setT_act_desc(rs.getString("t_act_desc"));
						trip.setT_act_city(rs.getInt("t_act_city"));
						trip.setT_act_loc(rs.getString("t_act_loc"));
//						trip.setT_act_time(rs.getTimestamp("t_act_time"));
						trip.setT_act_time(rs.getString("t_act_time"));
						trip.setT_act_ppl(rs.getInt("t_act_ppl"));
						trip.setT_act_pic_one(rs.getBytes("t_act_pic_one"));
						trip.setT_act_pic_two(rs.getBytes("t_act_pic_two"));
						trip.setT_act_bdg(rs.getInt("t_act_bdg"));
						trip.setT_act_status(rs.getString("t_act_status"));	
						return trip;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	
	//查詢活動種類(狗或貓)
	@Override
	public Trip selectByActType(Integer t_act_type) {
		final String sql = "select * from FurrEver.trip where t_act_type = ?";
		try (
				Connection conn = DriverManager.getConnection(url,user,password);
//				Connection conn =  ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1,t_act_type);
				try (
					ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						Trip trip = new Trip();
						trip.setT_act_id(rs.getInt("t_act_id"));
						trip.setUid(rs.getInt("uid"));
						trip.setT_act_type(rs.getInt("t_act_type"));
						trip.setT_act_name(rs.getString("t_act_name"));
						trip.setT_act_desc(rs.getString("t_act_desc"));
						trip.setT_act_city(rs.getInt("t_act_city"));
						trip.setT_act_loc(rs.getString("t_act_loc"));
//						trip.setT_act_time(rs.getTimestamp("t_act_time"));
						trip.setT_act_time(rs.getString("t_act_time"));
						trip.setT_act_ppl(rs.getInt("t_act_ppl"));
						trip.setT_act_pic_one(rs.getBytes("t_act_pic_one"));
						trip.setT_act_pic_two(rs.getBytes("t_act_pic_two"));
						trip.setT_act_bdg(rs.getInt("t_act_bdg"));
						trip.setT_act_status(rs.getString("t_act_status"));		
						return trip;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}

	//查詢最熱門活動
	@Override
	public List<Trip> selectHotAct() {
		final String sql = "select tr.*,count(*) from trip tr  \n"
				+ "join participant pt on tr.t_act_id = pt.t_act_id\n"
				+ "group by tr.t_act_id order by count(*) desc ";
		try (
				Connection conn = DriverManager.getConnection(url,user,password);
//				Connection conn =  ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
				List<Trip> list = new ArrayList<>();
				while (rs.next()) {
					Trip trip = new Trip();
					trip.setT_act_id(rs.getInt("t_act_id"));
					trip.setUid(rs.getInt("uid"));
					trip.setT_act_type(rs.getInt("t_act_type"));
					trip.setT_act_name(rs.getString("t_act_name"));
					trip.setT_act_desc(rs.getString("t_act_desc"));
					trip.setT_act_city(rs.getInt("t_act_city"));
					trip.setT_act_loc(rs.getString("t_act_loc"));
//					trip.setT_act_time(rs.getTimestamp("t_act_time"));
					trip.setT_act_time(rs.getString("t_act_time"));
					trip.setT_act_ppl(rs.getInt("t_act_ppl"));
					trip.setT_act_pic_one(rs.getBytes("t_act_pic_one"));
					trip.setT_act_pic_two(rs.getBytes("t_act_pic_two"));
					trip.setT_act_bdg(rs.getInt("t_act_bdg"));
					trip.setT_act_status(rs.getString("t_act_status"));		

					list.add(trip);
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}

	//查詢最新活動
	@Override
	public List<Trip> selectNewAct() {
		final String sql = "select * from trip tr  \n"
							+ "order by tr.t_act_time desc";
		try (
				Connection conn = DriverManager.getConnection(url,user,password);
//				Connection conn =  ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
				List<Trip> list = new ArrayList<>();
				while (rs.next()) {
					Trip trip = new Trip();
					trip.setT_act_id(rs.getInt("t_act_id"));
					trip.setUid(rs.getInt("uid"));
					trip.setT_act_type(rs.getInt("t_act_type"));
					trip.setT_act_name(rs.getString("t_act_name"));
					trip.setT_act_desc(rs.getString("t_act_desc"));
					trip.setT_act_city(rs.getInt("t_act_city"));
					trip.setT_act_loc(rs.getString("t_act_loc"));
//					trip.setT_act_time(rs.getTimestamp("t_act_time"));
					trip.setT_act_time(rs.getString("t_act_time"));
					trip.setT_act_ppl(rs.getInt("t_act_ppl"));
					trip.setT_act_pic_one(rs.getBytes("t_act_pic_one"));
					trip.setT_act_pic_two(rs.getBytes("t_act_pic_two"));
					trip.setT_act_bdg(rs.getInt("t_act_bdg"));
					trip.setT_act_status(rs.getString("t_act_status"));		

					list.add(trip);
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}

	//查詢活動地區下拉式選單(台北、新北市)
	@Override
	public Trip selectActCity(Integer t_act_city) {
		final String sql = "select * from FurrEver.trip where t_act_city = ?";
		try (
				Connection conn = DriverManager.getConnection(url,user,password);
//				Connection conn =  ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1,t_act_city);
				try (
					ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						Trip trip = new Trip();
						trip.setT_act_id(rs.getInt("t_act_id"));
						trip.setUid(rs.getInt("uid"));
						trip.setT_act_type(rs.getInt("t_act_type"));
						trip.setT_act_name(rs.getString("t_act_name"));
						trip.setT_act_desc(rs.getString("t_act_desc"));
						trip.setT_act_city(rs.getInt("t_act_city"));
						trip.setT_act_loc(rs.getString("t_act_loc"));
//						trip.setT_act_time(rs.getTimestamp("t_act_time"));
						
						trip.setT_act_ppl(rs.getInt("t_act_ppl"));
						trip.setT_act_pic_one(rs.getBytes("t_act_pic_one"));
						trip.setT_act_pic_two(rs.getBytes("t_act_pic_two"));
						trip.setT_act_bdg(rs.getInt("t_act_bdg"));
						trip.setT_act_status(rs.getString("t_act_status"));		
						return trip;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}		
	
	//新增活動內容
	@Override
	public Integer insertAct(Trip trip) {
		final String sql = "INSERT INTO trip (uid, t_act_type, t_act_name, t_act_desc, t_act_city, t_act_loc, t_act_time, t_act_ppl, t_act_pic_one, t_act_pic_two, t_act_bdg, t_act_status)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";				
		try (
			Connection conn = DriverManager.getConnection(url,user,password);
//			Connection conn =  ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, trip.getUid());
			pstmt.setInt(2, trip.getT_act_type());
			pstmt.setString(3, trip.getT_act_name());
			pstmt.setString(4, trip.getT_act_desc());
			pstmt.setInt(5, trip.getT_act_city());
			pstmt.setString(6, trip.getT_act_loc());
//			pstmt.setTimestamp(7, trip.getT_act_time());
			pstmt.setString(7, trip.getT_act_time());
			pstmt.setInt(8, trip.getT_act_ppl());
			pstmt.setBytes(9, trip.getT_act_pic_one());
			pstmt.setBytes(10, trip.getT_act_pic_two());
			pstmt.setInt(11, trip.getT_act_bdg());
			pstmt.setString(12, trip.getT_act_status());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	//修改活動內容 
	public Integer updateAct(Trip trip) {
		int rowCount = 0;
		String sql = "update trip "
				+ "set t_act_type = ?, t_act_name = ?, t_act_desc = ?, t_act_city = ?, t_act_loc = ?, t_act_time = ?, t_act_ppl = ?, t_act_pic_one = ?, t_act_pic_two = ?, t_act_bdg = ?"
				+ "where t_act_id = ?;";
		try (
			 Connection conn = DriverManager.getConnection(url,user,password);
//			 Connection conn =  ds.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, trip.getT_act_type());
			pstmt.setString(2, trip.getT_act_name());
			pstmt.setString(3, trip.getT_act_desc());
			pstmt.setInt(4, trip.getT_act_city());
			pstmt.setString(5, trip.getT_act_loc());
//			pstmt.setTimestamp(6, trip.getT_act_time());
			pstmt.setString(6, trip.getT_act_time());
			pstmt.setInt(7, trip.getT_act_ppl());
			pstmt.setBytes(8, trip.getT_act_pic_one());
			pstmt.setBytes(9, trip.getT_act_pic_two());
			pstmt.setInt(10, trip.getT_act_bdg());
			pstmt.setInt(12, trip.getT_act_id());
			rowCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount = -1;
	}

	//活動到期下架
	public Integer updateActDeadLine(Trip trip) {
		int rowCount = 0;
		String sql = "update trip "
				+ "set t_act_status = ? "
				+ "where t_act_id = ?;";
		try (
			 Connection conn = DriverManager.getConnection(url,user,password);
//			 Connection conn =  ds.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, trip.getT_act_status());
			pstmt.setInt(2, trip.getT_act_id());
			rowCount = pstmt.executeUpdate();
			return 	rowCount;//記得要return rowCount，不然會直接到return到399行的return rowCount = -1;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowCount = -1;
	}
	
}

