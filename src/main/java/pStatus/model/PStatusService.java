package pStatus.model;

import java.util.List;

public class PStatusService {

	private PStatusDAO_interface dao;

	public PStatusService() {
		dao = new PStatusDAO();
	}

	public PStatusVO addPro(Integer ps_id, String ps_name) {
		PStatusVO pStatusVO = new PStatusVO();
		pStatusVO.setPs_id(ps_id);
		pStatusVO.setPs_name(ps_name);
		dao.insert(pStatusVO);
		return pStatusVO;
	}

	public PStatusVO getOnePro(Integer ps_id) {
		return dao.findByPrimaryKey(ps_id);
	}

	public List<PStatusVO> getAll() {
		return dao.getAll();
	}
}
