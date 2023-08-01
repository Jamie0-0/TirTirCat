package articles.vo;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "reply_report", catalog = "FurrEver")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyReport extends Core{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rrep_id;
	
	private Integer rrep_reply_id;
	
	private Integer rrep_user_id;
	
	private String rrep_reason;
	@Transient
	private Timestamp rrep_time;
	@Transient
	private String rrep_status;

}
