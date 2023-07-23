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
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends Core{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer com_id;
	@Column
	private Integer com_art_id;
	@Column 
	private Integer com_user_id;
	@Column 
	private String com_content;
	@Column (insertable = false)
	private Timestamp com_date_time;
	@Column (insertable = false)
	private Integer com_rep_count;
	@Column (insertable = false)
	private String com_status;
    @Transient // 告訴 Hibernate 不需要映射到數據庫
    private String u_name;
    
    @ManyToOne
    @JoinColumn(name = "COM_USER_ID", insertable = false, updatable = false)  // 自己對應到USER的PK
    private User user; // 對應到 User 實體

}
