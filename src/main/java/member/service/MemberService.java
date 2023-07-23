package member.service;

import java.sql.Date;
import java.util.List;

import member.vo.Member;

public interface MemberService {
	
	String login(String email, String password);
	
	Member register(String email, String username, String password, String phone, String address, Date birth, String gender, String aboutme);
	
	boolean validate(String email, String password, String password2, String name, String phone, String gender, Date birth,
			String addr);
	List<String> getErrorMsgs();
	
	boolean edit(Member member);
	
}
