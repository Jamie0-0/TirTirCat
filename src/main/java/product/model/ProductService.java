package product.model;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import product.model.*;

public class ProductService {

	private ProductDAO_interface dao;

	public ProductService() {
		dao = new ProductDAO();
	}
	
	
	public List<ProductVO> searchLatest() {
		return dao.searchLatest();
	}
			
			
			
	

	public ProductVO addPro(Integer p_m_id, String p_name,
			Integer p_price, Integer p_stock, Integer p_type,
			Integer p_class, String p_des,
			byte[] p_pic_one, byte[] p_pic_two, byte[] p_pic_three, byte[] p_pic_four) {

		ProductVO productVO = new ProductVO();

		productVO.setP_m_id(p_m_id);
		productVO.setP_name(p_name);
		productVO.setP_price(p_price);
		productVO.setP_stock(p_stock);
		productVO.setP_type(p_type);
		productVO.setP_class(p_class);
		productVO.setP_des(p_des);
		productVO.setP_status(2);
		productVO.setP_pic_one(p_pic_one);
		productVO.setP_pic_two(p_pic_two);
		productVO.setP_pic_three(p_pic_three);
		productVO.setP_pic_four(p_pic_four);
		productVO.setP_1("");
		productVO.setP_2("");
		productVO.setP_3("");
		dao.insert(productVO);

		return productVO;
	}

	public ProductVO updatePro(String p_name,
			Integer p_price, Integer p_stock, Integer p_type,
			Integer p_class, String p_des, Integer p_status, Integer p_id,
			byte[] p_pic_one,byte[] p_pic_two,byte[] p_pic_three,byte[] p_pic_four) {

		ProductVO productVO = new ProductVO();
		productVO.setP_name(p_name);
		productVO.setP_price(p_price);
		productVO.setP_stock(p_stock);
		productVO.setP_type(p_type);
		productVO.setP_class(p_class);
		productVO.setP_des(p_des);
		productVO.setP_status(p_status);
		productVO.setP_pic_one(p_pic_one);
		productVO.setP_pic_two(p_pic_two);
		productVO.setP_pic_three(p_pic_three);
		productVO.setP_pic_four(p_pic_four);

		LocalDateTime localDateTime = LocalDateTime.now();
        productVO.setP_upload_time(localDateTime);

		productVO.setP_id(p_id);
		dao.update(productVO);

		return productVO;
	}

	public void deletePro(Integer p_id) {
		dao.delete(p_id);
	}

	public ProductVO getOnePro(Integer p_id) {
		return dao.findByPrimaryKey(p_id);
	}
	
	public List<ProductVO> getOnePro2(Integer p_id,Integer p_status,Integer p_class) {
		return dao.findByPrimaryKey2(p_id,p_status,p_class);
	}

	public List<ProductVO> getAll() {
		return dao.getAll();
	}
}
