package articles.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import articles.vo.Article;

public class ArticlesDaoImpl implements ArticlesDao {
	private DataSource ds;
	
	private String SelectHot ="select * from db01.articles where art_status = '1' order by art_like desc";
	
	public ArticlesDaoImpl() {
		
		try{
			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/javaFramework");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Article> selectHot(){
		var list = new ArrayList<Article>();
		try(
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SelectHot);
				ResultSet rs = pstmt.executeQuery();
				){
				Article article = new Article();
			while(rs.next()) {
				
				article.setArt_id(rs.getInt("art_id"));
				article.setArt_user_id(rs.getInt("art_user_id"));
				article.setArt_title(rs.getString("art_title"));
				article.setArt_content(rs.getString("art_content"));
				article.setArt_po_time(rs.getTimestamp("art_po_time"));
				article.setArt_like(rs.getInt("art_like"));
				article.setArt_status(rs.getString("art_status"));
				list.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
			
		
	}
}