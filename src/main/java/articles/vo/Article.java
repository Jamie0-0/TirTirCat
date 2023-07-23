package articles.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "articles", catalog = "FurrEver")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article extends Core{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer art_id;
	@Column 
	private Integer art_user_id;
	@Transient
	private Integer uid;
	@Transient
	private String u_name;
	@Column
	private  String art_title;
	@Column
	private String art_content;
	@Column (insertable = false)
	private Timestamp art_po_time;
	@Column (insertable = false)
	private Integer art_like;
	@Column(insertable = false)
	private Integer art_rep_count;
	@Column (insertable = false)
	private String art_status;	
	
}
