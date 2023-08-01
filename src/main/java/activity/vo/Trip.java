package activity.vo;

import java.sql.Timestamp;
import java.util.Arrays;

public class Trip {
	
	private Integer t_act_id;
	private Integer uid;
	private Integer t_act_type;
	private String t_act_name;
	private String t_act_desc;
	private Integer t_act_city;
	private String t_act_loc;
	private String t_act_time;
//	private Timestamp t_act_time;
	private Integer t_act_ppl;
	private byte[] t_act_pic_one;
	private byte[] t_act_pic_two;
	private Integer t_act_bdg;
	private String t_act_status;
	private Integer gm_id;
	private Timestamp gm_date;
	private String t_1;
	private String t_2;
	private String t_3;	
	
	private boolean successful;
	private String message;
	
	public boolean isSuccessful() {
		return successful;
	}
	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public Integer getT_act_id() {
		return t_act_id;
	}
	public void setT_act_id(Integer t_act_id) {
		this.t_act_id = t_act_id;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getT_act_type() {
		return t_act_type;
	}
	public void setT_act_type(Integer t_act_type) {
		this.t_act_type = t_act_type;
	}
	public String getT_act_name() {
		return t_act_name;
	}
	public void setT_act_name(String t_act_name) {
		this.t_act_name = t_act_name;
	}
	public String getT_act_desc() {
		return t_act_desc;
	}
	public void setT_act_desc(String t_act_desc) {
		this.t_act_desc = t_act_desc;
	}
	public Integer getT_act_city() {
		return t_act_city;
	}
	public void setT_act_city(Integer t_act_city) {
		this.t_act_city = t_act_city;
	}
	public String getT_act_loc() {
		return t_act_loc;
	}
	public void setT_act_loc(String t_act_loc) {
		this.t_act_loc = t_act_loc;
	}
	public String getT_act_time() {
		return t_act_time;
	}
	public void setT_act_time(String t_act_time) {
		this.t_act_time = t_act_time;
	}
//	public Timestamp getT_act_time() {
//		return t_act_time;
//	}
//	public void setT_act_time(Timestamp t_act_time) {
//		this.t_act_time = t_act_time;
//	}
	
	public Integer getT_act_ppl() {
		return t_act_ppl;
	}
	public void setT_act_ppl(Integer t_act_ppl) {
		this.t_act_ppl = t_act_ppl;
	}
	public byte[] getT_act_pic_one() {
		return t_act_pic_one;
	}
	public void setT_act_pic_one(byte[] t_act_pic_one) {
		this.t_act_pic_one = t_act_pic_one;
	}
	public byte[] getT_act_pic_two() {
		return t_act_pic_two;
	}
	public void setT_act_pic_two(byte[] t_act_pic_two) {
		this.t_act_pic_two = t_act_pic_two;
	}
	public Integer getT_act_bdg() {
		return t_act_bdg;
	}
	public void setT_act_bdg(Integer t_act_bdg) {
		this.t_act_bdg = t_act_bdg;
	}
	public String getT_act_status() {
		return t_act_status;
	}
	public void setT_act_status(String t_act_status) {
		this.t_act_status = t_act_status;
	}
	public Integer getGm_id() {
		return gm_id;
	}
	public void setGm_id(Integer gm_id) {
		this.gm_id = gm_id;
	}
	public Timestamp getGm_date() {
		return gm_date;
	}
	public void setGm_date(Timestamp gm_date) {
		this.gm_date = gm_date;
	}
	public String getT_1() {
		return t_1;
	}
	public void setT_1(String t_1) {
		this.t_1 = t_1;
	}
	public String getT_2() {
		return t_2;
	}
	public void setT_2(String t_2) {
		this.t_2 = t_2;
	}
	public String getT_3() {
		return t_3;
	}
	public void setT_3(String t_3) {
		this.t_3 = t_3;
	}
	
	//用來測試用的
	@Override
	public String toString() {
		return "Trip [t_act_id=" + t_act_id + ", uid=" + uid + ", t_act_type=" + t_act_type + ", t_act_name="
				+ t_act_name + ", t_act_desc=" + t_act_desc + ", t_act_city=" + t_act_city + ", t_act_loc=" + t_act_loc
				+ ", t_act_time=" + t_act_time + ", t_act_ppl=" + t_act_ppl + ", t_act_pic_one="
				+ Arrays.toString(t_act_pic_one) + ", t_act_pic_two=" + Arrays.toString(t_act_pic_two) + ", t_act_bdg="
				+ t_act_bdg + ", t_act_status=" + t_act_status + ", gm_id=" + gm_id + ", gm_date=" + gm_date + ", t_1="
				+ t_1 + ", t_2=" + t_2 + ", t_3=" + t_3 + "]";
	}	
	
	
	
}
