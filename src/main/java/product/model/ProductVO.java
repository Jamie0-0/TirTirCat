package product.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ProductVO {
    private Integer a;
    private Integer b;
    private Integer c;
    private Integer d;
    private Integer p_id;
    private Integer p_m_id;
    private String p_name;
    private Integer p_price;
    private Integer p_stock;
	private Integer p_count;
	private Integer p_type;
	private Integer p_class;
	private LocalDateTime p_upload_time;
	private String p_des;
	private Integer p_status;
	private byte[]  p_pic_one;
	private byte[]  p_pic_two;
	private byte[]  p_pic_three;
	private byte[]  p_pic_four;
	private String p_1;
	private String p_2;
	private String p_3;

	public Integer getA() {
		return a;
	}
//	public void setA(Integer a) {
//		this.a = a;
//	}
    
	public Integer getB() {
		return b;
	}
//	public void setB(Integer b) {
//		this.b = b;
//	}
    
	public Integer getC() {
		return c;
	}
//	public void setC(Integer c) {
//		this.c = c;
//	}
    
	public Integer getD() {
		return d;
	}
//	public void setD(Integer d) {
//		this.d = d;
//	}

	public Integer getP_id() {
		return p_id;
	}
//	public void setP_id(Integer p_id) {
//		this.p_id = p_id;
//	}

	public Integer getP_m_id() {
		return p_m_id;
	}
//	public void setP_m_id(Integer p_m_id) {
//		this.p_m_id = p_m_id;
//	}

	public String getP_name() {
		return p_name;
	}
//	public void setP_name(String p_name) {
//		this.p_name = p_name;
//	}

	public Integer getP_price() {
		return p_price;
	}
//	public void setP_price(Integer p_price) {
//		this.p_price = p_price;
//	}

	public Integer getP_stock() {
		return p_stock;
	}
//	public void setP_stock(Integer p_stock) {
//		this.p_stock = p_stock;
//	}

	public Integer getP_count() {
		return p_count;
	}
//	public void setP_count(Integer p_count) {
//		this.p_count = p_count;
//	}

	public Integer getP_type() {
		return p_type;
	}
//	public void setP_type(Integer p_type) {
//		this.p_type = p_type;
//	}

	public Integer getP_class() {
		return p_class;
	}
//	public void setP_class(Integer p_class) {
//		this.p_class = p_class;
//	}

	public LocalDateTime getP_upload_time() {
		return p_upload_time;
	}
//	public void setP_upload_time(LocalDateTime localDateTime) {
//		this.p_upload_time = localDateTime;
//	}

	public String getP_des() {
		return p_des;
	}
//	public void setP_des(String p_des) {
//		this.p_des = p_des;
//	}

	public Integer getP_status() {
		return p_status;
	}
//	public void setP_status(Integer p_status) {
//		this.p_status = p_status;
//	}

	public byte[]  getP_pic_one() {
		return p_pic_one;
	}
//	public void setP_pic_one(byte[] p_pic_one) {
//		this.p_pic_one = p_pic_one;
//	}
	
	public byte[]  getP_pic_two() {
		return p_pic_two;
	}
//	public void setP_pic_two(byte[] p_pic_two) {
//		this.p_pic_two = p_pic_two;
//	}
	
	public byte[]  getP_pic_three() {
		return p_pic_three;
	}
//	public void setP_pic_three(byte[] p_pic_three) {
//		this.p_pic_three = p_pic_three;
//	}
	
	public byte[]  getP_pic_four() {
		return p_pic_four;
	}
//	public void setP_pic_four(byte[] p_pic_four) {
//		this.p_pic_four = p_pic_four;
//	}
	
	public String getP_1() {
		return p_1;
	}
//	public void setP_1(String p_1) {
//		this.p_1 = p_1;
//	}

	public String getP_2() {
		return p_2;
	}
//	public void setP_2(String p_2) {
//		this.p_2 = p_2;
//	}

	public String getP_3() {
		return p_3;
	}
//	public void setP_3(String p_3) {
//		this.p_3 = p_3;
//	}

	private ProductVO() {
		super();
	}

	private ProductVO(ProductVO.Builder builder) {
		a = builder.a;
		b = builder.b;
		c = builder.c;
		d = builder.d;
		p_id = builder.p_id;
		p_m_id = builder.p_m_id;
		p_name = builder.p_name;
		p_price = builder.p_price;
		p_stock = builder.p_stock;
		p_count = builder.p_count;
		p_type = builder.p_type;
		p_class = builder.p_class;
		p_upload_time = builder.p_upload_time;
		p_des = builder.p_des;
		p_status = builder.p_status;
		p_pic_one = builder.p_pic_one;
		p_pic_two = builder.p_pic_two;
		p_pic_three = builder.p_pic_three;
		p_pic_four = builder.p_pic_four;
		p_1 = builder.p_1;
		p_2 = builder.p_2;
		p_3 = builder.p_3;
	}
	
	public static class Builder {
	    private Integer a = 0;
	    private Integer b = 0;
	    private Integer c = 0;
	    private Integer d = 0;
	    private Integer p_id = 0;
	    private Integer p_m_id = 0;
	    private String p_name = "";
	    private Integer p_price = 0;
	    private Integer p_stock = 0;
		private Integer p_count = 0;
		private Integer p_type = 0;
		private Integer p_class = 0;
		private LocalDateTime p_upload_time = null;
		private String p_des = "";
		private Integer p_status = 0;
		private byte[]  p_pic_one = null;
		private byte[]  p_pic_two = null;
		private byte[]  p_pic_three = null;
		private byte[]  p_pic_four = null;
		private String p_1 = "";
		private String p_2 = "";
		private String p_3 = "";
		
		public ProductVO.Builder setA(Integer a) {
			this.a = a;
			return this;
		}
		
		public ProductVO.Builder setB(Integer b) {
			this.b = b;
			return this;
		}
		
		public ProductVO.Builder setC(Integer c) {
			this.c = c;
			return this;
		}
		
		public ProductVO.Builder setD(Integer d) {
			this.d = d;
			return this;
		}
		
		public ProductVO.Builder setP_id(Integer p_id) {
			this.p_id = p_id;
			return this;
		}
		
		public ProductVO.Builder setP_m_id(Integer p_m_id) {
			this.p_m_id = p_m_id;
			return this;
		}
		
		public ProductVO.Builder setP_name(String p_name) {
			this.p_name = p_name;
			return this;
		}

		public ProductVO.Builder setP_price(Integer p_price) {
			this.p_price = p_price;
			return this;
		}
		
		public ProductVO.Builder setP_stock(Integer p_stock) {
			this.p_stock = p_stock;
			return this;
		}
		
		public ProductVO.Builder setP_count(Integer p_count) {
			this.p_count = p_count;
			return this;
		}
		
		public ProductVO.Builder setP_type(Integer p_type) {
			this.p_type = p_type;
			return this;
		}
		
		public ProductVO.Builder setP_class(Integer p_class) {
			this.p_class = p_class;
			return this;
		}
		
		public ProductVO.Builder setP_upload_time(LocalDateTime p_upload_time) {
			this.p_upload_time = p_upload_time;
			return this;
		}
		
		public ProductVO.Builder setP_des(String p_des) {
			this.p_des = p_des;
			return this;
		}
		
		public ProductVO.Builder setP_status(Integer p_status) {
			this.p_status = p_status;
			return this;
		}
		
		public ProductVO.Builder setP_pic_one(byte[] p_pic_one) {
			this.p_pic_one = p_pic_one;
			return this;
		}
		
		public ProductVO.Builder setP_pic_two(byte[] p_pic_two) {
			this.p_pic_two = p_pic_two;
			return this;
		}
		
		public ProductVO.Builder setP_pic_three(byte[] p_pic_three) {
			this.p_pic_three = p_pic_three;
			return this;
		}
		
		public ProductVO.Builder setP_pic_four(byte[] p_pic_four) {
			this.p_pic_four = p_pic_four;
			return this;
		}
		
		public ProductVO.Builder setP_1(String p_1) {
			this.p_1 = p_1;
			return this;
		}
		
		public ProductVO.Builder setP_2(String p_2) {
			this.p_2 = p_2;
			return this;
		}

		public ProductVO.Builder setP_3(String p_3) {
			this.p_3 = p_3;
			return this;
		}

		public ProductVO build() {
			return new ProductVO(this);
		}
	}
}
