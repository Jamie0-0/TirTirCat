package articles.vo;

import java.sql.Date;
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
@Table(name = "user", catalog = "FurrEver")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Core{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer uid;
	@Transient
	private String  u_phone;
	@Column
	 private String u_name;
	@Transient
	private String u_pwd;
	@Transient
	private String u_email;
	@Transient
	private String u_address;
	@Transient
	private Date u_birth;
	@Transient
	private String u_gender;
	@Transient
	private Timestamp u_reg;
	@Transient
	private byte[] u_pic;
	@Transient
	private Integer u_report;
	@Transient
	private String u_status;
	@Transient
	private Integer gm_ID;
	@Transient
	private Timestamp gm_date;
	@Transient
	private String u_1;
	@Transient
	private String u_2;
	@Transient
	private String u_3;
	
}
