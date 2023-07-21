package pMapping.model;

import java.time.LocalDateTime;
import java.util.List;

import product.model.ProductDAO;
import product.model.ProductDAO_interface;
import product.model.ProductVO;

public class PMappingService {

	private PMappingDAO_interface dao;

	public PMappingService() {
		dao = new PMappingDAO();
	}

	public PMappingVO addPro(Integer pm_id, String pm_name) {
		PMappingVO pMappingVO = new PMappingVO();
		pMappingVO.setPm_id(pm_id);
		pMappingVO.setPm_name(pm_name);
		dao.insert(pMappingVO);
		return pMappingVO;
	}

	public PMappingVO getOnePro(Integer pm_id) {
		return dao.findByPrimaryKey(pm_id);
	}

	public List<PMappingVO> getAll() {
		return dao.getAll();
	}
}
