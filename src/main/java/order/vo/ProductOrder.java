package order.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import articles.vo.User;
import product_fe.vo.ProductLike;

@Entity
@Table(name = "product_order")
public class ProductOrder implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer order_id;
	@Column
	private Integer order_uid;
	@Column
	private String order_r_name;
	@Column
	private String order_r_phone;
	@Column
	private String order_r_addr;
	@Column
	private Integer order_dfee;
	@Column
	private Integer order_t;
	@Column
	private String order_s;
	@Column
	private String order_pay;
	@Column(insertable = false)
	private Integer gm_id;
	@Column(insertable = false)
	private Timestamp gm_date;
	@Column
	private String order_1;
	@Column
	private String order_2;
	@Column
	private String order_3;
	// 定義訂單與子訂單之間的關係
    @OneToMany(mappedBy = "productOrder")
    private List<SubOrder> subOrders;

//    // 定義訂單與商品收藏清單之間的關係
//    @OneToMany(mappedBy = "productOrder")
//    private List<ProductLike> productLikes;

    // 定義訂單與會員之間的關係
    @ManyToOne
    @JoinColumn(name = "order_uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private User user;

	public ProductOrder() {
	}

	public ProductOrder(Integer order_uid, String order_r_name, String order_r_phone,
			String order_r_addr, Integer order_dfee, Integer order_t, String order_s, String order_pay) {
//		this.order_id = order_id;
		this.order_uid = order_uid;
		this.order_r_name = order_r_name;
		this.order_r_phone = order_r_phone;
		this.order_r_addr = order_r_addr;
		this.order_dfee = order_dfee;
		this.order_t = order_t;
		this.order_s = order_s;
		this.order_pay = order_pay;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getOrder_uid() {
		return order_uid;
	}

	public void setOrder_uid(Integer order_uid) {
		this.order_uid = order_uid;
	}

	public String getOrder_r_name() {
		return order_r_name;
	}

	public void setOrder_r_name(String order_r_name) {
		this.order_r_name = order_r_name;
	}

	public String getOrder_r_phone() {
		return order_r_phone;
	}

	public void setOrder_r_phone(String order_r_phone) {
		this.order_r_phone = order_r_phone;
	}

	public String getOrder_r_addr() {
		return order_r_addr;
	}

	public void setOrder_r_addr(String order_r_addr) {
		this.order_r_addr = order_r_addr;
	}

	public Integer getOrder_dfee() {
		return order_dfee;
	}

	public void setOrder_dfee(Integer order_dfee) {
		this.order_dfee = order_dfee;
	}

	public Integer getOrder_t() {
		return order_t;
	}

	public void setOrder_t(Integer order_t) {
		this.order_t = order_t;
	}

	public String getOrder_s() {
		return order_s;
	}

	public void setOrder_s(String order_s) {
		this.order_s = order_s;
	}

	public String getOrder_pay() {
		return order_pay;
	}

	public void setOrder_pay(String order_pay) {
		this.order_pay = order_pay;
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

	public String getOrder_1() {
		return order_1;
	}

	public void setOrder_1(String order_1) {
		this.order_1 = order_1;
	}

	public String getOrder_2() {
		return order_2;
	}

	public void setOrder_2(String order_2) {
		this.order_2 = order_2;
	}

	public String getOrder_3() {
		return order_3;
	}

	public void setOrder_3(String order_3) {
		this.order_3 = order_3;
	}
	
	public List<SubOrder> getSubOrders() {
		return subOrders;
	}

	public void setSubOrders(List<SubOrder> subOrders) {
		this.subOrders = subOrders;
	}

	@Override
	public String toString() {
		return "ProductOrder [order_id=" + order_id + ", order_uid=" + order_uid + ", order_r_name=" + order_r_name
				+ ", order_r_phone=" + order_r_phone + ", order_r_addr=" + order_r_addr + ", order_dfee=" + order_dfee
				+ ", order_t=" + order_t + ", order_s=" + order_s + ", order_pay=" + order_pay + ", gm_id=" + gm_id
				+ ", gm_date=" + gm_date + ", order_1=" + order_1 + ", order_2=" + order_2 + ", order_3=" + order_3
				+ ", subOrders=" + subOrders + "]";
	}


}
