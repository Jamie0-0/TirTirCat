package articles.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@Column
	private Integer com_id;
	@Column
	private Integer com_art_id;
	@Column 
	private Integer com_user_id;
	@Column 
	private String com_content;
	@Column 
	private Timestamp com_date_time;
	@Column 
	private Integer com_rep_count;
	@Column 
	private String com_status;

}
