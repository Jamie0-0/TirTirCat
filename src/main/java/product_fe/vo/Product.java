package product_fe.vo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import master.vo.Master;
import order.vo.SubProduct;
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
	private Integer p_id;
    @Column
	private Integer p_m_id;
    @Column
	private String p_name;
    @Column
	private Integer p_price;
    @Column
	private Integer p_stock;
    @Column
	private Integer p_count;
    @Column
	private String p_type;
    @Column
	private String p_class;
    @Column
	private Timestamp p_upload_time;
    @Column
	private String p_des;
    @Column
	private String p_status;
    @Column
	private byte[]  p_pic_one;
    @Column
	private byte[]  p_pic_two;
    @Column
	private byte[]  p_pic_three;
    @Column
	private byte[]  p_pic_four;
    @Column
	private String p_1;
    @Column
	private String p_2;
    @Column
	private String p_3;
@Transient
private String m_name;
// 定義商品與子訂單之間的關係
@OneToMany(mappedBy = "product")
private List<SubProduct> subProducts;

//// 定義商品與商品收藏清單之間的關係
//@OneToMany(mappedBy = "product")
//private List<ProductLike> productLikes;

//// 定義商品與廠商之間的關係
//@ManyToOne
//@JoinColumn(name = "p_m_id", referencedColumnName = "m_id", insertable = false, updatable = false)
//private Master master;
	
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
