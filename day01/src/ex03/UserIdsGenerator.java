package ex03;

public class UserIdsGenerator {
	private static int counter;

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

}
