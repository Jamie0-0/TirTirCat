package gb.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class GbVO implements Serializable {
	private Integer gb_id; // 團購編號
	private Integer gb_p_id; // 商品編號
	private Integer gb_s_price; // 特價金額
	private Integer gb_c_max; // 數量門檻
	private Timestamp gb_time_start; // 開團時間
	private Timestamp gb_time_end; // 結團時間
	private Integer gb_status; // 成團狀態
	
	private ProductVO product; // 新增 ProductVO 屬性

	public Integer getGb_id() {
		return gb_id;
	}

	public void setGb_id(Integer gb_id) {
		this.gb_id = gb_id;
	}

	public Integer getGb_p_id() {
		return gb_p_id;
	}

	public void setGb_p_id(Integer gb_p_id) {
		this.gb_p_id = gb_p_id;
	}

	public Integer getGb_s_price() {
		return gb_s_price;
	}

	public void setGb_s_price(Integer gb_s_price) {
		this.gb_s_price = gb_s_price;
	}

	public Integer getGb_c_max() {
		return gb_c_max;
	}

	public void setGb_c_max(Integer gb_c_max) {
		this.gb_c_max = gb_c_max;
	}

	public Timestamp getGb_time_start() {
		return gb_time_start;
	}

	public void setGb_time_start(Timestamp gb_time_start) {
		this.gb_time_start = gb_time_start;
	}

	public Timestamp getGb_time_end() {
		return gb_time_end;
	}

	public void setGb_time_end(Timestamp gb_time_end) {
		this.gb_time_end = gb_time_end;
	}

	public Integer getGb_status() {
		return gb_status;
	}

	public void setGb_status(Integer gb_status) {
		this.gb_status = gb_status;
	}

	
	public ProductVO getProduct() {
        return product;
    }

    public void setProduct(ProductVO product) {
        this.product = product;
    }

	public String getP_name() {
		return null;
	}
}
