package order.vo;

public class CartItem {
	private int item_p_id;
	private String item_p_name;
    private int item_p_m_id;
    private int item_p_price;
    private int item_quantity;
//    private int p_total_price;
    
	public int getItem_p_id() {
		return item_p_id;
	}
	public void setItem_p_id(int item_p_id) {
		this.item_p_id = item_p_id;
	}
	public int getItem_p_m_id() {
		return item_p_m_id;
	}
	public void setItem_p_m_id(int item_p_m_id) {
		this.item_p_m_id = item_p_m_id;
	}
	public int getItem_p_price() {
		return item_p_price;
	}
	public void setItem_p_price(int item_p_price) {
		this.item_p_price = item_p_price;
	}
	public int getItem_quantity() {
		return item_quantity;
	}
	public void setItem_quantity(int item_quantity) {
		this.item_quantity = item_quantity;
	}
	public String getItem_p_name() {
		return item_p_name;
	}
	public void setItem_p_name(String item_p_name) {
		this.item_p_name = item_p_name;
	}
    

//	public int getP_total_price() {
//		return p_total_price;
//	}
//	public void setP_total_price(int p_total_price) {
//		this.p_total_price = p_total_price;
//	}
}
