package master.service;

import java.util.LinkedList;
import java.util.List;

import master.dao.MasterDao;
import master.vo.Master;

public class MasterService {
	private MasterDao dao;
	List<String> errMsgs;

	public MasterService() {
		dao = new MasterDao();
		errMsgs = new LinkedList<String>();
	}

	public Master insert(Master master) {
		Master result = dao.selectByMgui(master.getMGui());
		
			if (master.getMEmail().equals(result.getMEmail())) {
				errMsgs.add("重複帳號");
			}
			if (master.getMGui().equals(result.getMGui())) {
				errMsgs.add("統編重複");
			}
			if (master.getMBankId().equals(result.getMBankId())) {
				errMsgs.add("銀行帳號重複");
			}
			if (master.getMManId().equals(result.getMManId())) {
				errMsgs.add("身份證字號重複");
			}
		
		if (errMsgs.isEmpty()) {
			return result;
		}
		return null;

	}

	public List<String> getErrorMsgs() {
		return errMsgs;
	}
}
