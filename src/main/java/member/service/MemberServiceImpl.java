package member.service;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import member.dao.MemberDao;
import member.dao.MemberDaoImpl;
import member.vo.Member;

public class MemberServiceImpl implements MemberService {
	private MemberDao dao;
	List<String> errorMsgs ;

	public MemberServiceImpl() {
		dao = new MemberDaoImpl();
		errorMsgs =	new LinkedList<String>();
	}

	public String login(String email, String password) {
		return dao.login(email, password);
	}

	@Override
	public Member register(String email, String username, String password, String phone, String address, Date birth,
			String gender) {
		Member member = new Member();
		member.setEmail(email);
		member.setName(username);
		member.setPassword(password);
		member.setPhone(phone);
		member.setAddr(address);
		member.setBirth(birth);
		member.setGender(gender);
		dao.insert(member);
		return member;
	}

	public boolean validate(String email, String password, String name, String phone, String gender, Date birth,
			String addr) {
		
		
		String emailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		if (email == null || email.trim().length() == 0) {
			errorMsgs.add("會員帳號不能空白");
		} else if (!email.trim().matches(emailReg)) {
			errorMsgs.add("信箱必須符合信箱格式");
		}
		if (password == null || password.trim().length() == 0) {
			errorMsgs.add("會員密碼不能空白");
		}
		String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
		if (name == null || name.trim().length() == 0) {
			errorMsgs.add("會員姓名: 請勿空白");
		} else if (!name.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
			errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
		}
		String phoneReg = "^09\\d{8}$";
		if (phone == null || phone.trim().length() == 0) {
			errorMsgs.add("會員手機：請勿空白");
		} else if (!phone.trim().matches(phoneReg)) {
			errorMsgs.add("請符合手機格式");
		}
		if (addr == null || addr.trim().length() == 0) {
			errorMsgs.add("地址不能空白");
		}
		
		return errorMsgs.isEmpty();
	}

	public List<String> getErrorMsgs() {
        return errorMsgs;
    }

}
