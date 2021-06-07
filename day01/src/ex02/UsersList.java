package ex02;

public interface UsersList {
	void    addUser(User user);
	User    getUserByID(Integer id) throws UsersArrayList.UserNotFoundException;
	User    getUserByIndex(Integer index);
	Integer length();
}
