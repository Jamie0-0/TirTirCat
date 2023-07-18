package articles.vo;

import java.sql.Timestamp;

public class Comment {
	private Integer com_id;
	private Integer com_art_id;
	private Integer com_user_id;
	private String com_content;
	private Timestamp com_date_time;
	private Integer com_rep_count;
	private String com_status;

	public Integer getCom_id() {
		return com_id;
	}

	public void setCom_id(Integer com_id) {
		this.com_id = com_id;
	}

	public Integer getCom_art_id() {
		return com_art_id;
	}

	public void setCom_art_id(Integer com_art_id) {
		this.com_art_id = com_art_id;
	}

	public Integer getCom_user_id() {
		return com_user_id;
	}

	public void setCom_user_id(Integer com_user_id) {
		this.com_user_id = com_user_id;
	}

	public String getCom_content() {
		return com_content;
	}

	public void setCom_content(String com_content) {
		this.com_content = com_content;
	}

	public Timestamp getCom_date_time() {
		return com_date_time;
	}

	public void setCom_date_time(Timestamp com_date_time) {
		this.com_date_time = com_date_time;
	}

	public Integer getCom_rep_count() {
		return com_rep_count;
	}

	public void setCom_rep_count(Integer com_rep_count) {
		this.com_rep_count = com_rep_count;
	}

	public String getCom_status() {
		return com_status;
	}

	public void setCom_status(String com_status) {
		this.com_status = com_status;
	}
}
