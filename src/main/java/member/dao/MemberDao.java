package member.dao;

import member.vo.Member;

public interface MemberDao {
	Member login(String email, String password);

	boolean insert(Member member);

	Member selectByUserName(String name);

	int update(Member member);
	
	Member selectByEmail(String email);

}