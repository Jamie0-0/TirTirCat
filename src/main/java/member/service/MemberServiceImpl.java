package member.service;

import java.io.BufferedReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import member.dao.MemberDao;
import member.dao.MemberDaoImpl;
import member.vo.Member;

public class MemberServiceImpl implements MemberService {
	private MemberDao dao;
	List<String> errorMsgs;

	public MemberServiceImpl() {
		dao = new MemberDaoImpl();
		errorMsgs = new LinkedList<String>();
	}

	public Member login(Member member) {
		errorMsgs.clear();
		String email = member.getEmail();
		String password = member.getPassword();
		Member result = dao.selectByEmail(email);
		if (result == null) {
			errorMsgs.add("查無帳號");
		} else if (email.equals(result.getEmail())) {
			if (!password.equals(result.getPassword())) {
				errorMsgs.add("密碼不正確");
			}
		}
		if (email.isBlank()) {
			errorMsgs.add("帳號未輸入");
		}
		if (password.isBlank()) {
			errorMsgs.add("密碼未輸入");
		}
		if (!errorMsgs.isEmpty()) {
			getErrorMsgs();
			return null;
		}
		return dao.login(email, password);
	}

	@Override
	public Member register(String email, String username, String password, String phone, String address, Date birth,
			String gender, String aboutme) {
		errorMsgs.clear();
		Member member = new Member();
		member.setEmail(email);
		member.setName(username);
		member.setPassword(password);
		member.setPhone(phone);
		member.setAddr(address);
		member.setBirth(birth);
		member.setGender(gender);
		member.setAbout(aboutme);
		dao.insert(member);
		return member;
	}

	public boolean validate(String email, String password, String name, String phone, String gender, Date birth,
			String addr) {

		errorMsgs.clear();
		String emailReg = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		if (email == null || email.trim().length() == 0) {
			errorMsgs.add("會員帳號不能空白");
		} else if (!email.trim().matches(emailReg)) {
			errorMsgs.add("信箱必須符合信箱格式");
		} else if (dao.selectByEmail(email) != null) {
			errorMsgs.add("帳號已存在");
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
		if (birth == null || "".equals(birth)) {
			errorMsgs.add("生日不能空白");
		}
		if (addr == null || addr.trim().length() == 0) {
			errorMsgs.add("地址不能空白");
		}
		return errorMsgs.isEmpty();
	}

	public List<String> getErrorMsgs() {
		return errorMsgs;
	}

	@Override
	public boolean edit(BufferedReader reader) {
		JsonElement jsonElement = JsonParser.parseReader(reader);
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		String uEmail = jsonObject.get("email").getAsString();
		String uName = jsonObject.get("name").getAsString();
		String uPwd = jsonObject.get("password").getAsString();
		String uPhone = jsonObject.get("phone").getAsString();
		String uPic = jsonObject.get("uPic").getAsString();
		String base64 = uPic.substring(uPic.indexOf(",") + 1);
		byte[] uicBytes = Base64.getDecoder().decode(base64);

		String uAbout = jsonObject.get("about").getAsString();
		String uBirth = jsonObject.get("birth").getAsString();
		Integer uid = jsonObject.get("uid").getAsInt();

		Member member = new Member();
		member.setEmail(uEmail);
		member.setName(uName);
		member.setPassword(uPwd);
		member.setPhone(uPhone);
		member.setAbout(uAbout);
//		member.setBirth(uBirth);
		member.setuPic(uicBytes);
		member.setUid(uid);
		int result = dao.update(member);
		return result > 0;
	}

}
