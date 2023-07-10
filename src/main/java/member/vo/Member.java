package member.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class Member {
	private Integer uid;
	private String uPhone;
	private String uName;
	private String uPwd;
	private String uEmail;
	private String uAddress;
	private Date uBirth;
	private String uGender;
	private Timestamp uReg;
	private byte[] uPic;
	private Integer u_report;
	private String u_status;
	private Integer gm_ID;
	private Timestamp gmDate;
	private String u1;
	private String u2;
	private String u3;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getuPhone() {
		return uPhone;
	}

	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuPwd() {
		return uPwd;
	}

	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}

	public String getuEmail() {
		return uEmail;
	}

	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}

	public String getuAddress() {
		return uAddress;
	}

	public void setuAddress(String uAddress) {
		this.uAddress = uAddress;
	}

	public Date getuBirth() {
		return uBirth;
	}

	public void setuBirth(Date uBirth) {
		this.uBirth = uBirth;
	}

	public String getuGender() {
		return uGender;
	}

	public void setuGender(String uGender) {
		this.uGender = uGender;
	}

	public Timestamp getuReg() {
		return uReg;
	}

	public void setuReg(Timestamp uReg) {
		this.uReg = uReg;
	}

	public byte[] getuPic() {
		return uPic;
	}

	public void setuPic(byte[] uPic) {
		this.uPic = uPic;
	}

	public Integer getU_report() {
		return u_report;
	}

	public void setU_report(Integer u_report) {
		this.u_report = u_report;
	}

	public String getU_status() {
		return u_status;
	}

	public void setU_status(String u_status) {
		this.u_status = u_status;
	}

	public Integer getGm_ID() {
		return gm_ID;
	}

	public void setGm_ID(Integer gm_ID) {
		this.gm_ID = gm_ID;
	}

	public Timestamp getGmDate() {
		return gmDate;
	}

	public void setGmDate(Timestamp gmDate) {
		this.gmDate = gmDate;
	}

	public String getU1() {
		return u1;
	}

	public void setU1(String u1) {
		this.u1 = u1;
	}

	public String getU2() {
		return u2;
	}

	public void setU2(String u2) {
		this.u2 = u2;
	}

	public String getU3() {
		return u3;
	}

	public void setU3(String u3) {
		this.u3 = u3;
	}

}
