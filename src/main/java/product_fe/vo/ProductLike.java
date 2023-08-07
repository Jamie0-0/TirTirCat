package product_fe.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import articles.vo.User;

@Entity
@Table(name = "product_like")
public class ProductLike implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    private int pl_uid;
    @Id
    private int pl_p_id;

    // 定義商品收藏清單與商品之間的關係
    @ManyToOne
    @JoinColumn(name = "pl_p_id", referencedColumnName = "p_id", insertable = false, updatable = false)
    private Product product;

    // 定義商品收藏清單與會員之間的關係
    @ManyToOne
    @JoinColumn(name = "pl_uid", referencedColumnName = "uid", insertable = false, updatable = false)
    private User user;

	public int getPl_uid() {
		return pl_uid;
	}

	public void setPl_uid(int pl_uid) {
		this.pl_uid = pl_uid;
	}

	public int getPl_p_id() {
		return pl_p_id;
	}

	public void setPl_p_id(int pl_p_id) {
		this.pl_p_id = pl_p_id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


}