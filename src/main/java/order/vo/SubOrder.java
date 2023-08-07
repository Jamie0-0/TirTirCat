package order.vo;

import java.io.Serializable;
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

@Entity
@Table(name = "sub_order")
public class SubOrder implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
	private int so_order_id;
    @Column(name = "so_order_num")
	private int so_order_num;
    @Column
	private int so_m_id;
    @Column
	private int order_num;
    @Column
	private String order_status;
    @Column
	private String so_1;
    @Column
	private String so_2;
    @Column
	private String so_3;
    
    // 定義子訂單與訂單之間的關係
    @ManyToOne
    @JoinColumn(name = "so_order_num", referencedColumnName = "order_id", insertable = false, updatable = false)
    private ProductOrder productOrder;
    
    // 定義SubOrder與SubProduct之間的一對多關聯
    @OneToMany(mappedBy = "subOrder")
    private List<SubProduct> subProducts;

//    // 定義子訂單與廠商之間的關係
//    @ManyToOne
//    @JoinColumn(name = "so_m_id", referencedColumnName = "m_id", insertable = false, updatable = false)
//    private Master master;

	public SubOrder() {
	}

	public SubOrder( int so_m_id, int order_num, String order_status) {
		super();
		this.so_m_id = so_m_id;
		this.order_num = order_num;
		this.order_status = order_status;
	}
	
	public SubOrder(int so_order_num, int so_m_id, int order_num, String order_status) {
		super();
		this.so_order_num = so_order_num;
		this.so_m_id = so_m_id;
		this.order_num = order_num;
		this.order_status = order_status;
	}

	public int getSo_order_id() {
		return so_order_id;
	}

	public void setSo_order_id(int so_order_id) {
		this.so_order_id = so_order_id;
	}

	public int getSo_order_num() {
		return so_order_num;
	}

	public void setSo_order_num(int so_order_num) {
		this.so_order_num = so_order_num;
	}

	public int getSo_m_id() {
		return so_m_id;
	}

	public void setSo_m_id(int so_m_id) {
		this.so_m_id = so_m_id;
	}

	public int getOrder_num() {
		return order_num;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getSo_1() {
		return so_1;
	}

	public void setSo_1(String so_1) {
		this.so_1 = so_1;
	}

	public String getSo_2() {
		return so_2;
	}

	public void setSo_2(String so_2) {
		this.so_2 = so_2;
	}

	public String getSo_3() {
		return so_3;
	}

	public void setSo_3(String so_3) {
		this.so_3 = so_3;
	}
	
public List<SubProduct> getSubProducts() {
	return subProducts;
}

public void setSubProducts(List<SubProduct> subProducts) {
	this.subProducts = subProducts;
}

@Override
public String toString() {
	return "SubOrder [so_order_id=" + so_order_id + ", so_order_num=" + so_order_num + ", so_m_id=" + so_m_id
			+ ", order_num=" + order_num + ", order_status=" + order_status + ", so_1=" + so_1 + ", so_2=" + so_2
			+ ", so_3=" + so_3 + ", productOrder=" + productOrder + ", subProducts=" + subProducts + "]";
}



}
