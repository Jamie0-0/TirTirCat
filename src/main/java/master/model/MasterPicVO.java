package master.model;

import java.time.LocalDateTime;

import product.model.ProductVO;

public class MasterPicVO {
    private Integer b;
    private Integer c;
    private String p_name;
	private String p_pic_one;

	public Integer getB() {
		return b;
	}
    
	public Integer getC() {
		return c;
	}

	public String getP_name() {
		return p_name;
	}


	public String getP_pic_one() {
		return p_pic_one;
	}

	private MasterPicVO() {
		super();
	}

	private MasterPicVO(MasterPicVO.Builder builder) {
		b = builder.b;
		c = builder.c;
		p_name = builder.p_name;
		p_pic_one = builder.p_pic_one;
	}
	
	public static class Builder {
	    private Integer b = 0;
	    private Integer c = 0;
	    private String p_name = "";
		private String  p_pic_one = null;

		public MasterPicVO.Builder setB(Integer b) {
			this.b = b;
			return this;
		}
		
		public MasterPicVO.Builder setC(Integer c) {
			this.c = c;
			return this;
		}
		
		public MasterPicVO.Builder setP_name(String p_name) {
			this.p_name = p_name;
			return this;
		}

		public MasterPicVO.Builder setP_pic_one(String p_pic_one) {
			this.p_pic_one = p_pic_one;
			return this;
		}

		public MasterPicVO build() {
			return new MasterPicVO(this);
		}
	}
}
