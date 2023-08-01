package articles.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "articles_report", catalog = "FurrEver")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticlesReport extends Core{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer rep_id;
	
	private Integer rep_art_id;
	
	private Integer rep_user_id;
	
	private String rep_reason;
	@Column (insertable = false)
	private Timestamp rep_time;
	@Column (insertable = false)
	private String rep_status;
 

}
