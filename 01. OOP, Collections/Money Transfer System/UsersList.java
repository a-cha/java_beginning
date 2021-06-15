package ex04;

public interface UsersList {
	void    addUser(User user);
	User    getUserByID(Integer id);
	User    getUserByIndex(Integer index);
	Integer length();
}
