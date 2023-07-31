package member.vo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import pet.vo.Pet;

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
	private Integer uReport;
	private Integer uStatus;
	private Integer gmID;
	private Timestamp gmDate;
	private String about;
	private String u2;
	private String u3;

//	@OneToMany
//	@JoinColumn(name = "pet_uid",
//			referencedColumnName = "uid")
//	private List<Pet> pets;
	
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

	public Integer getuReport() {
		return uReport;
	}

	public void setuReport(Integer u_report) {
		this.uReport = u_report;
	}

	public Integer getuStatus() {
		return uStatus;
	}

	public void setuStatus(Integer u_status) {
		this.uStatus = u_status;
	}

	public Integer getGmID() {
		return gmID;
	}

	public void setGmID(Integer gm_ID) {
		this.gmID = gm_ID;
	}

	public Timestamp getGmDate() {
		return gmDate;
	}

	public void setGmDate(Timestamp gmDate) {
		this.gmDate = gmDate;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
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
