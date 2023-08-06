package order.vo;

import java.util.List;

public class Orders {
	private List<CartItem> cartlist;
	private ProductOrder productOrder;

	public List<CartItem> getCartlist() {
		return cartlist;
	}

	public void setCartlist(List<CartItem> cartlist) {
		this.cartlist = cartlist;
	}

	public ProductOrder getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(ProductOrder productOrder) {
		this.productOrder = productOrder;
	}

}
