package member.service;

import java.sql.Date;
import java.util.List;

import member.vo.Member;

public interface MemberService {
	
	public String login(String email, String password);
	
	public Member register(String email, String username, String password, String phone, String address, Date birth, String gender);
	
	public boolean validate(String email, String password, String name, String phone, String gender, Date birth,
			String addr);
	public List<String> getErrorMsgs();
}
