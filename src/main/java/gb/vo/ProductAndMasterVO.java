package gb.vo;

import java.io.Serializable;

public class ProductAndMasterVO implements Serializable {
    private ProductVO productVO; // 导入 ProductVO

    private Integer mId; // 廠商編號
    private String mName; // 廠商名稱


    public ProductVO getProductVO() {
        return productVO;
    }

    public void setProductVO(ProductVO productVO) {
        this.productVO = productVO;
    }

    public Integer getMId() {
        return mId;
    }

    public void setMId(Integer mId) {
        this.mId = mId;
    }

    public String getMName() {
        return mName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }



}

