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
    


}
