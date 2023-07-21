package pType.model;

import java.util.List;

import pMapping.model.PMappingDAO;
import pMapping.model.PMappingDAO_interface;
import pMapping.model.PMappingVO;

public class PTypeService {

	private PTypeDAO_interface dao;

	public PTypeService() {
		dao = new PTypeDAO();
	}

	public PTypeVO addPro(Integer pt_id, String pt_name) {
		PTypeVO pTypeVO = new PTypeVO();
		pTypeVO.setPt_id(pt_id);
		pTypeVO.setPt_name(pt_name);
		dao.insert(pTypeVO);
		return pTypeVO;
	}

	public PTypeVO getOnePro(Integer pt_id) {
		return dao.findByPrimaryKey(pt_id);
	}

	public List<PTypeVO> getAll() {
		return dao.getAll();
	}
}
