package member.dao;

public interface MemberDao {
	
	
	String authenticate(String email, String password);
}