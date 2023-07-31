package master.model;

import java.time.LocalDateTime;
import java.util.List;

public class MasterService {

	private MasterDao_interface dao;

	public MasterService() {
		dao = new MasterDao();
	}


	public MasterVO updatePro(Integer m_id,String m_name,String m_pwd
			,String m_gui,String m_bank_name,String m_bank_id,String m_address
			,String m_man_id,String m_man_name,String m_emil,String m_phone){

		MasterVO masterVO = new MasterVO.Builder()
											.setM_id(m_id)
											.setM_name(m_name)
											.setM_pwd(m_pwd)
											.setM_gui(m_gui)
											.setM_bank_name(m_bank_name)
											.setM_bank_id(m_bank_id)
											.setM_address(m_address)
											.setM_address(m_man_id)
											.setM_address(m_man_name)
											.setM_address(m_emil)
											.setM_address(m_phone)
											.build();
		dao.update(masterVO);

		return masterVO;
	}

	public MasterVO getOnePro(Integer m_id) {
		return dao.findByPrimaryKey(m_id);
	}
}
