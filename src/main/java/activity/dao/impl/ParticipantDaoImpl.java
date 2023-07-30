package activity.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import activity.dao.ParticipantDao;
import activity.vo.Participant;
import activity.vo.Trip;

public class ParticipantDaoImpl implements ParticipantDao {

	//連線持目前有問題
	private static DataSource ds;
	
	//彬華JDBC寫死的DriverManager寫法
	private final static String url="jdbc:mysql://localhost:3306/Furrever?serverTimezone=Asia/Taipei";
	private final static String user="root";
	private final static String password="password";	
	
	//連線持目前有問題
		static {
			try {
				ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/FurrEver");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		
	//測試當前程式用
	public static void main(String[] args) {
	
//	//show活動目前參加人數有多少人
//	Integer pp = new ParticipantDaoImpl().selectActJoin(5, "0");
//	System.out.println(pp.toString());  
		
	//參加活動測試
    String sql = "INSERT INTO participant (t_act_id, uid, uid_join)\n"
    		+ "VALUES (?, ?, ?);";		
    Participant pp =new Participant(); 
    
    pp.setT_act_id(3);
    pp.setUid(2);
    pp.setUid_join("0");

    Integer pp1 = new ParticipantDaoImpl().imInAct(pp);
    System.out.println(pp1); 	
		
	}
	
	//使用Servlet，需將DriverManager改為Data Source，調整如下
	//-> Connection conn = ds.getConnection();
	//push到git時記得把main方法整個拿掉呦！
		
	//顯示活動參加人數目前有多少
	@Override
	public Integer selectActJoin(Integer t_act_id, String uid_join) {
		final String sql = "select count(*) from participant\n"
				+ " where t_act_id = ? AND uid_join = ? \n"
				+ " group by t_act_id ";
		try (
				Connection conn = DriverManager.getConnection(url,user,password);
//				Connection conn =  ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, 5);
				pstmt.setString(2, "0");
				try (
					ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
				Integer count = rs.getInt(1);
						return count;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}	
	
	//參加活動
	@Override
	public Integer imInAct(Participant participant) {
		final String sql = "INSERT INTO participant (t_act_id, uid, uid_join)\n"
				+ "VALUES (?, ?, ?);";				
		try (
			Connection conn = DriverManager.getConnection(url,user,password);
//				Connection conn =  ds.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, participant.getT_act_id());
			pstmt.setInt(2, participant.getUid());
			pstmt.setString(3, participant.getUid_join());
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	

}
