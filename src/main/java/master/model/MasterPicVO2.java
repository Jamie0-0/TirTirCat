package master.model;

import java.time.LocalDateTime;

import product.model.ProductVO;

public class MasterPicVO2 {
	private String order_r_name;
    private String order_r_phone;
    private String order_r_addr;
	private String order_t;
	private String order_pay;
	private String u_pic;
	

	public String getOrder_r_name() {
		return order_r_name;
	}
    
	public String getOrder_r_phone() {
		return order_r_phone;
	}
	
	public String getOrder_r_addr() {
		return order_r_addr;
	}
	
	public String getOrder_t() {
		return order_t;
	}
	
	public String getOrder_pay() {
		return order_pay;
	}
	
	public String getU_pic() {
		return u_pic;
	}

	private MasterPicVO2() {
		super();
	}

	private MasterPicVO2(MasterPicVO2.Builder builder) {
		order_r_name = builder.order_r_name;
		order_r_phone = builder.order_r_phone;
		order_r_addr = builder.order_r_addr;
		order_t = builder.order_t;
		u_pic = builder.u_pic;
		order_pay = builder.order_pay;
	}

	public static class Builder {
	    private String order_r_name = "";
	    private String order_r_phone = "";
	    private String order_r_addr = "";
	    private String order_t = "";
	    private String order_pay = "";
	    private String u_pic = "";
	    
	    

		public MasterPicVO2.Builder setOrder_r_name(String order_r_name) {
			this.order_r_name = order_r_name;
			return this;
		}

		public MasterPicVO2.Builder setOrder_r_phone(String order_r_phone) {
			this.order_r_phone = order_r_phone;
			return this;
		}
		
		public MasterPicVO2.Builder setOrder_r_addr(String order_r_addr) {
			this.order_r_addr = order_r_addr;
			return this;
		}
		
		public MasterPicVO2.Builder setOrder_t(String order_t) {
			this.order_t = order_t;
			return this;
		}
		
		public MasterPicVO2.Builder setOrder_pay(String order_pay) {
			this.order_pay = order_pay;
			return this;
		}
		
		public MasterPicVO2.Builder setU_pic(String u_pic) {
			this.u_pic = u_pic;
			return this;
		}
		
		public MasterPicVO2 build() {
			return new MasterPicVO2(this);
		}
	}
}
