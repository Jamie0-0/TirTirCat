package member.service;

import java.io.BufferedReader;
import java.sql.Date;
import java.util.List;

import member.vo.Member;

public interface MemberService {
	
	Member login(Member member);
	
	Member register(String email, String username, String password, String phone, String address, Date birth, String gender, String aboutme);
	
	boolean validate(String email, String password, String name, String phone, String gender, Date birth,
			String addr);
	List<String> getErrorMsgs();
	
	boolean edit(BufferedReader reader);
	
}
