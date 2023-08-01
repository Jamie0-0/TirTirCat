package gb.service;

import java.util.List;

import gb.vo.GbAndProductVO;
import gb.vo.GbVO;



public interface GBService {
	void addGb(GbVO gbVO);

	void updateGb(GbVO gbVO);

	void deleteGb(Integer gb_id);

	GbVO getGbByPrimaryKey(Integer gb_id);

	List<GbVO> getAllGb();
	
	List<GbAndProductVO> getAllGbJoinProduct(); 
	
	List<GbAndProductVO> getAllGbJoinProductWithBase64();
}