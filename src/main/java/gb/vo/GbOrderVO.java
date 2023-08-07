package gb.vo;

import java.io.Serializable;
import java.util.Date;

public class GbOrderVO implements Serializable {
	    private Integer gb_id; // 團購訂單編號
	    private Integer uid; // 會員編號
	    private Integer gb_order_id; // 團購編號
	    private Date gb_date; // 訂單日期
	    private Integer gb_t; // 訂單總金額
	    private String gb_s; // 訂單狀態
	    private String gb_pay; // 付款狀態
	    private Integer gb_p_num; // 商品數量
	    private Integer gb_p_dfee; // 運費金額
	    
	    private GbVO gbVO;

	    public Integer getGb_id() {
	        return gb_id;
	    }

	    public void setGb_id(Integer gb_id) {
	        this.gb_id = gb_id;
	    }

	    public Integer getUid() {
	        return uid;
	    }

	    public void setUid(Integer uid) {
	        this.uid = uid;
	    }

	    public Integer getGb_order_id() {
	        return gb_order_id;
	    }

	    public void setGb_order_id(Integer gb_order_id) {
	        this.gb_order_id = gb_order_id;
	    }

	    public Date getGb_date() {
	        return gb_date;
	    }

	    public void setGb_date(Date gb_date) {
	        this.gb_date = gb_date;
	    }

	    public Integer getGb_t() {
	        return gb_t;
	    }

	    public void setGb_t(Integer gb_t) {
	        this.gb_t = gb_t;
	    }

	    public String getGb_s() {
	        return gb_s;
	    }

	    public void setGb_s(String gb_s) {
	        this.gb_s = gb_s;
	    }

	    public String getGb_pay() {
	        return gb_pay;
	    }

	    public void setGb_pay(String gb_pay) {
	        this.gb_pay = gb_pay;
	    }

	    public Integer getGb_p_num() {
	        return gb_p_num;
	    }

	    public void setGb_p_num(Integer gb_p_num) {
	        this.gb_p_num = gb_p_num;
	    }

	    public Integer getGb_p_dfee() {
	        return gb_p_dfee;
	    }

	    public void setGb_p_dfee(Integer gb_p_dfee) {
	        this.gb_p_dfee = gb_p_dfee;
	    }
	    
	    public GbVO getGbVO() {
	        return gbVO;
	    }

	    public void setGbVO(GbVO gbVO) {
	        this.gbVO = gbVO;
	    }
	}

