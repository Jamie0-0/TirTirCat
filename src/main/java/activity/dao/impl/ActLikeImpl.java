package activity.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import activity.dao.ActLikeDao;
import activity.vo.ActLike;
import activity.vo.Trip;

public class ActLikeImpl implements ActLikeDao {

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
	public static void main(String args[]) {
	//show收藏的所有活動資料
		List<ActLike> al = new ActLikeImpl().selectAll();
		for(ActLike ac : al) {
			System.out.println(ac.toString());
		}	
	}
	
	//使用Servlet，需將DriverManager改為Data Source，調整如下
	//-> Connection conn = ds.getConnection();
	//push到git時記得把main方法整個拿掉呦！
	
	@Override
	public List<ActLike> selectAll() {
		final String sql = "select * from FurrEver.act_like";
		try (
				Connection conn = DriverManager.getConnection(url,user,password);
//				Connection conn =  ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
				List<ActLike> list = new ArrayList<>();
				while (rs.next()) {
					ActLike actlike = new ActLike();
					actlike.setT_act_id(rs.getInt("t_act_id"));
					actlike.setUid(rs.getInt("uid"));			

					list.add(actlike);
				}
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
}
