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
@Table (name = "com_report", catalog = "FurrEver")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComReport {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer crep_id;
	
	private Integer crep_com_id;
	
	private Integer crep_user_id;
	
	private String crep_reason;
	@Transient
	private Timestamp crep_time;
	@Transient
	private String crep_status;

}
