package edu.school21.numbers;

public class NumberWorker {
	public NumberWorker() {}

	public boolean isPrime(int number) throws IllegalNumberException {
		if (number < 2)
			throw new IllegalNumberException("Invalid number");
		if (number == 2)
			return true;

		if (number % 2 != 0) {
			int delim = 3;
			while (delim * delim < number && number % delim != 0) {
				delim += 2;
			}
			return number < delim * delim;
		} else {
			return false;
		}
	}

	public int digitsSum(int number) {
		int result = 0;

		while (number != 0) {
			result += number % 10;
			number /= 10;
		}

		return result;
	}

	static class IllegalNumberException extends RuntimeException {
		public IllegalNumberException(String message) {
			super("IllegalNumberException: " + message);
		}
	}

}
