package member.service;

import java.util.List;

import member.dao.MemberDao;
import member.dao.MemberDaoImpl;
import member.vo.Member;

public class MemberServiceImpl implements MemberService {
	private MemberDao dao;
	
	public MemberServiceImpl() {
		dao = new MemberDaoImpl();
	}
	@Override
//	public List<Member> login() {
//		
//		return dao.login();
//	}
	
	public String login(String email, String password) {
		return dao.authenticate(email, password);
	}
	
}
