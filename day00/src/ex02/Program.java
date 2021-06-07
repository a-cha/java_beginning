import java.util.Scanner;

public class Program {

	static int sumOfDigits(int n) {
		int res = 0, len = numLen(n);

		for (int i = 0; i < len; i++) {
			res += n % 10;
			n /= 10;
		}
		return res;
	}

	static int numLen(int n) {
		int len = 1;

		while ((n /= 10) != 0) {
			len++;
		}
		return len;
	}

	static boolean isPrime(int num) {
		int     delim;

		if (num < 2 || num % 2 == 0) {
			return false;
		}

		delim = 3;

		while (num % delim != 0) {
			delim += 2;
		}

		return num == delim;
	}


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int     n, counter = 0;

		while ((n = scanner.nextInt()) != 42) {
			if (isPrime(sumOfDigits(n))) {
				counter++;
			}
		}
		System.out.println("Count of coffee-request – " + counter);
	}

}
