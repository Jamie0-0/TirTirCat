package order.vo;

import com.google.gson.JsonArray;

public class Orders {

	private ProductOrder productOrder;
	private SubOrder subOrder;
	private SubProduct subProduct;
	private JsonArray cartlist;

	public ProductOrder getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(ProductOrder productOrder) {
		this.productOrder = productOrder;
	}

	public SubOrder getSubOrder() {
		return subOrder;
	}

	public void setSubOrder(SubOrder subOrder) {
		this.subOrder = subOrder;
	}

	public SubProduct getSubProduct() {
		return subProduct;
	}

	public void setSubProduct(SubProduct subProduct) {
		this.subProduct = subProduct;
	}

	public JsonArray getCartlist() {
		return cartlist;
	}

	public void setCartlist(JsonArray cartlist) {
		this.cartlist = cartlist;
	}



}
