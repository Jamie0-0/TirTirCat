package master.vo;

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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MASTER")
public class Master {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "m_id")
	private Integer mId;
	@Column(name = "m_name")
	private String mName;
	@Column(name = "m_pwd")
	private String mPwd;
	@Column(name = "m_gui")
	private String mGui;
	@Column(name = "m_bank_name")
	private Integer mBankName;
	@Column(name = "m_bank_id")
	private String mBankId;
	@Column(name = "m_address")
	private String mAddress;
	@Column(name = "m_reg")
	private Timestamp mReg;
	@Column(name = "m_man_id")
	private String mManId;
	@Column(name = "m_man_name")
	private String mManName;
	@Column(name = "m_email")
	private String mEmail;
	@Column(name = "m_phone")
	private String mPhone;
	@Column(name = "m_status" ,insertable = false)
	private String mStatus;
	
}
