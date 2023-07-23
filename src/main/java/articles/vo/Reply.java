package articles.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "com_reply", catalog = "FurrEver")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reply extends Core{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer reply_id;
	@Column
	private Integer reply_com_id;
	@Column 
	private Integer reply_user_id;
	@Column 
	private String reply_content;
	@Column 
	private Timestamp reply_date_time;
	@Column 
	private Integer reply_rep_count;
	@Column 
	private String reply_status;
    @Transient // 告訴 Hibernate 不需要映射到數據庫
    private String u_name;
	
    @ManyToOne
    @JoinColumn(name = "REPLY_USER_ID", insertable = false, updatable = false)
    private User user; // 對應到 User 實體

	public Integer getReply_id() {
		return reply_id;
	}

	public void setReply_id(Integer reply_id) {
		this.reply_id = reply_id;
	}

	public Integer getReply_com_id() {
		return reply_com_id;
	}

	public void setReply_com_id(Integer reply_com_id) {
		this.reply_com_id = reply_com_id;
	}

	public Integer getReply_user_id() {
		return reply_user_id;
	}

	public void setReply_user_id(Integer reply_user_id) {
		this.reply_user_id = reply_user_id;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public Timestamp getReply_date_time() {
		return reply_date_time;
	}

	public void setReply_date_time(Timestamp reply_date_time) {
		this.reply_date_time = reply_date_time;
	}

	public Integer getReply_rep_count() {
		return reply_rep_count;
	}

	public void setReply_rep_count(Integer reply_rep_count) {
		this.reply_rep_count = reply_rep_count;
	}

	public String getReply_status() {
		return reply_status;
	}

	public void setReply_status(String reply_status) {
		this.reply_status = reply_status;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
    
    
}
