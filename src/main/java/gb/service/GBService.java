package gb.service;

import java.util.List;

import gb.vo.GbAndProductVO;
import gb.vo.GbOrderVO;
import gb.vo.GbVO;
import gb.vo.ProductAndMasterVO;
import gb.vo.ProductVO;



public interface GBService {
	void addGb(GbVO gbVO);

	void updateGb(GbVO gbVO);

	void deleteGb(Integer gb_id);

	GbVO getGbByPrimaryKey(Integer gb_id);

	List<GbVO> getAllGb();
	
	List<GbAndProductVO> getAllGbJoinProduct(); 
	
	List<GbAndProductVO> getAllGbJoinProductWithBase64();
	
	//product join master
	List<ProductAndMasterVO> getProductsAndMasters();
	
	//gb join gborder
	List<GbOrderVO> getAllGbOrdersWithGbDetails();

	boolean insertGbOrder(GbOrderVO gbOrder); 
}