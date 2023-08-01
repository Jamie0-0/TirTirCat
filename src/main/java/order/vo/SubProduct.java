package order.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import product_fe.vo.Product;

@Entity
@Table(name = "sub_product")
public class SubProduct {
	@Id
	@Column
	private int order_id;
	@Id
	@Column
	private int p_p_id;
	@Column
	private int p_m_stock;
	@Column
	private int p_m_price;
	@Column
	private String sub1;
	@Column
	private String sub2;
	@Column
	private String sub3;
	// 定義子訂單明細與子訂單之間的關係
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "so_order_id", insertable = false, updatable = false)
    private SubOrder subOrder;

//    // 定義子訂單明細與商品之間的關係
    @ManyToOne
    @JoinColumn(name = "p_p_id", referencedColumnName = "p_id", insertable = false, updatable = false)
    private Product product;


	public SubProduct() {
	}

	public SubProduct(int order_id, int p_p_id, int p_m_stock, int p_m_price, String sub1, String sub2, String sub3) {
		this.order_id = order_id;
		this.p_p_id = p_p_id;
		this.p_m_stock = p_m_stock;
		this.p_m_price = p_m_price;
		this.sub1 = sub1;
		this.sub2 = sub2;
		this.sub3 = sub3;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getP_p_id() {
		return p_p_id;
	}

	public void setP_p_id(int p_p_id) {
		this.p_p_id = p_p_id;
	}

	public int getP_m_stock() {
		return p_m_stock;
	}

	public void setP_m_stock(int p_m_stock) {
		this.p_m_stock = p_m_stock;
	}

	public int getP_m_price() {
		return p_m_price;
	}

	public void setP_m_price(int p_m_price) {
		this.p_m_price = p_m_price;
	}

	public String getSub1() {
		return sub1;
	}

	public void setSub1(String sub1) {
		this.sub1 = sub1;
	}

	public String getSub2() {
		return sub2;
	}

	public void setSub2(String sub2) {
		this.sub2 = sub2;
	}

	public String getSub3() {
		return sub3;
	}

	public void setSub3(String sub3) {
		this.sub3 = sub3;
	}

}