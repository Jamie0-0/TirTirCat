package product_fe.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import articles.vo.User;
import member.vo.Member;

@Entity
@Table(name = "product_like")
public class ProductLike implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    private Integer pl_uid;
    @Id
    private Integer pl_p_id;

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
	
	@Override
	public String toString() {
		return "ProductLike [pl_uid=" + pl_uid + ", pl_p_id=" + pl_p_id + "]";
	}

}