package member.dao;

import member.vo.Member;

public interface MemberDao {
	String login(String email, String password);
	boolean insert(Member member);
}