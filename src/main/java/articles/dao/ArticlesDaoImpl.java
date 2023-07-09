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
	
	private String SelectHot ="select u.u_name, art_title, art_content, art_po_time, art_like from db01.articles a join db01.USER u on a.art_user_id = u.uid where art_status = '1' order by art_like desc";
	private String SelectNew ="select u.u_name, art_title, art_content, art_po_time, art_like from db01.articles a join db01.USER u on a.art_user_id = u.uid where art_status = '1' order by art_po_time desc";
	private String Search ="select u.u_name, art_title, art_content, art_po_time, art_like from db01.articles a join db01.USER u on a.art_user_id = u.uid where art_status = '1' and art_title like '%?%' order by art_like desc";
			
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
				
			while(rs.next()) {
				Article article = new Article();
				article.setU_name(rs.getString("u_name"));
				article.setArt_title(rs.getString("art_title"));
				article.setArt_content(rs.getString("art_content"));
				article.setArt_po_time(rs.getTimestamp("art_po_time"));
				article.setArt_like(rs.getInt("art_like"));
				list.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
			
		
	}

	@Override
	public List<Article> selectNew() {
		var list = new ArrayList<Article>();
		try(
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(SelectNew);
				ResultSet rs = pstmt.executeQuery();
				){
				
			while(rs.next()) {
				
				Article article = new Article();
				article.setU_name(rs.getString("u_name"));
				article.setArt_title(rs.getString("art_title"));
				article.setArt_content(rs.getString("art_content"));
				article.setArt_po_time(rs.getTimestamp("art_po_time"));
				article.setArt_like(rs.getInt("art_like"));
				list.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Article> search() {
		var list = new ArrayList<Article>();
		try(
				Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(Search);
				ResultSet rs = pstmt.executeQuery();
				){
				
			while(rs.next()) {
				
				Article article = new Article();
				article.setU_name(rs.getString("u_name"));
				article.setArt_title(rs.getString("art_title"));
				article.setArt_content(rs.getString("art_content"));
				article.setArt_po_time(rs.getTimestamp("art_po_time"));
				article.setArt_like(rs.getInt("art_like"));
				list.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}