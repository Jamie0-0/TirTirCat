package articles.vo;

import java.sql.Timestamp;

public class Article {
	
	private Integer art_id;
	private Integer art_user_id;
	private String u_name;
	private  String art_title;
	private String art_content;
	private Timestamp art_po_time;
	private Integer art_like;
	private Integer art_rep_count;
	private String art_status;
	
	
	public Integer getArt_id() {
		return art_id;
	}
	public void setArt_id(Integer art_id) {
		this.art_id = art_id;
	}
	public Integer getArt_user_id() {
		return art_user_id;
	}
	public void setArt_user_id(Integer art_user_id) {
		this.art_user_id = art_user_id;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public String getArt_title() {
		return art_title;
	}
	public void setArt_title(String art_title) {
		this.art_title = art_title;
	}
	public String getArt_content() {
		return art_content;
	}
	public void setArt_content(String art_content) {
		this.art_content = art_content;
	}
	public Timestamp getArt_po_time() {
		return art_po_time;
	}
	public void setArt_po_time(Timestamp art_po_time) {
		this.art_po_time = art_po_time;
	}
	public Integer getArt_like() {
		return art_like;
	}
	public void setArt_like(Integer art_like) {
		this.art_like = art_like;
	}
	public Integer getArt_rep_count() {
		return art_rep_count;
	}
	public void setArt_rep_count(Integer art_rep_count) {
		this.art_rep_count = art_rep_count;
	}
	public String getArt_status() {
		return art_status;
	}
	public void setArt_status(String art_status) {
		this.art_status = art_status;
	}

}
