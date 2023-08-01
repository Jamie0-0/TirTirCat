package product_fe.vo;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

public class Product {
	private Integer p_id;
	private Integer p_m_id;
	private String p_name;
	private Integer p_price;
	private Integer p_stock;
	private Integer p_count;
	private String p_type;
	private String p_class;
//	private LocalDateTime p_upload_time;
	private Timestamp p_upload_time;
	private String p_des;
	private String p_status;
	private byte[]  p_pic_one;
	private byte[]  p_pic_two;
	private byte[]  p_pic_three;
	private byte[]  p_pic_four;
	private String p_1;
	private String p_2;
	private String p_3;
private String m_name;
	
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	public Integer getP_m_id() {
		return p_m_id;
	}
	public void setP_m_id(Integer p_m_id) {
		this.p_m_id = p_m_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public Integer getP_price() {
		return p_price;
	}
	public void setP_price(Integer p_price) {
		this.p_price = p_price;
	}
	public Integer getP_stock() {
		return p_stock;
	}
	public void setP_stock(Integer p_stock) {
		this.p_stock = p_stock;
	}
	public Integer getP_count() {
		return p_count;
	}
	public void setP_count(Integer p_count) {
		this.p_count = p_count;
	}
	public String getP_type() {
		return p_type;
	}
	public void setP_type(String p_type) {
		this.p_type = p_type;
	}
	public String getP_class() {
		return p_class;
	}
	public void setP_class(String p_class) {
		this.p_class = p_class;
	}
	public Timestamp getP_upload_time() {
		return p_upload_time;
	}
	public void setP_upload_time(Timestamp p_upload_time) {
		this.p_upload_time = p_upload_time;
	}
	public String getP_des() {
		return p_des;
	}
	public void setP_des(String p_des) {
		this.p_des = p_des;
	}
	public String getP_status() {
		return p_status;
	}
	public void setP_status(String p_status) {
		this.p_status = p_status;
	}
	public byte[] getP_pic_one() {
		return p_pic_one;
	}
	public void setP_pic_one(byte[] p_pic_one) {
		this.p_pic_one = p_pic_one;
	}
	public byte[] getP_pic_two() {
		return p_pic_two;
	}
	public void setP_pic_two(byte[] p_pic_two) {
		this.p_pic_two = p_pic_two;
	}
	public byte[] getP_pic_three() {
		return p_pic_three;
	}
	public void setP_pic_three(byte[] p_pic_three) {
		this.p_pic_three = p_pic_three;
	}
	public byte[] getP_pic_four() {
		return p_pic_four;
	}
	public void setP_pic_four(byte[] p_pic_four) {
		this.p_pic_four = p_pic_four;
	}
	public String getP_1() {
		return p_1;
	}
	public void setP_1(String p_1) {
		this.p_1 = p_1;
	}
	public String getP_2() {
		return p_2;
	}
	public void setP_2(String p_2) {
		this.p_2 = p_2;
	}
	public String getP_3() {
		return p_3;
	}
	public void setP_3(String p_3) {
		this.p_3 = p_3;
	}
	
public String getM_name() {
		return m_name;
}
public void setM_name(String m_name) {
		this.m_name = m_name;
}

@Override
public int hashCode() {
	return Objects.hash(p_id);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Product other = (Product) obj;
	return Objects.equals(p_id, other.p_id);
}
	
}
