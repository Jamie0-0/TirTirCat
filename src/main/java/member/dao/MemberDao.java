package member.dao;

public interface MemberDao {
	

	boolean authenticate(String email, String password);
}