package member.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class Member {
	private Integer uid;
	private String phone;
	private String name;
	private String password;
	private String email;
	private String addr;
	private java.util.Date birth;
	private String gender;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Date getBirth() {
		java.sql.Date sqlDate = new java.sql.Date(birth.getTime());
		return sqlDate;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
