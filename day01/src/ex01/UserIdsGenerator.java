package ex01;

public class UserIdsGenerator {

	public static class UserIdsGeneratorHolder {
		public static final UserIdsGenerator holderInstance = new UserIdsGenerator();
	}

	public static UserIdsGenerator getInstance() {
		return UserIdsGeneratorHolder.holderInstance;
	}

	private UserIdsGenerator() {
		counter = 0;
	}

	public int generateId() {
		counter++;
		return counter;
	}

	private static int counter;
}
