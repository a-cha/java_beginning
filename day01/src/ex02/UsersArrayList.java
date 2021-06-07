package ex02;

public class UsersArrayList implements UsersList {
	private User[]  usersArray;
	private Integer length;
	private Integer maxLen;


	@Override
	public void addUser(User user) {
		if (length < maxLen) {
			usersArray[length] = user;
			length++;
		} else {
			maxLen = (int)((double)length * 1.5);
			User[] newUsersArray = new User[maxLen];
			for (int i = 0; i < length; i++) {
				newUsersArray[i] = usersArray[i];
			}
			newUsersArray[length] = user;
			length++;
			usersArray = newUsersArray;
		}
	}

	@Override
	public User getUserByID(Integer id) {
		for (int i = 0; i < length; i++) {
			if (usersArray[i].getId().equals(id)) {
				return usersArray[i];
			}
		}
		throw new UserNotFoundException("getUserByID: No user with id " + id + ", max: " + this.length);
	}

	@Override
	public User getUserByIndex(Integer index) {
		if (index < length) {
			return usersArray[index];
		} else {
			return null;
		}
	}

	@Override
	public Integer length() { return length; }

	UsersArrayList() {
		length = 0;
		maxLen = 10;
		usersArray = new User[maxLen];
	}

	static class UserNotFoundException extends RuntimeException {

		public UserNotFoundException(String m) {
			message = m;
		}

		public String toString(){
			return ("UsersArrayList:" + message);
		}

		private static String message;
	}
}
