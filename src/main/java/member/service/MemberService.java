package member.service;

import java.util.List;

import member.vo.Member;

public interface MemberService {
	
//	public List<Member> login();
	public String login(String email, String password);
}
