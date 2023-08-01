package activity.vo;

public class ActLike {

	private Integer t_act_id;
	private Integer uid;
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
	
	@Override
	public String toString() {
		return "ActLike [t_act_id=" + t_act_id + ", uid=" + uid + "]";
	}
	
	
}
