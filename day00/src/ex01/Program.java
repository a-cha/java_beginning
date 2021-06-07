import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int		n;
		int		delim;
		int		counter;

		n = scanner.nextInt();

		if (n < 2) {
			System.err.println("IllegalArgument");
			System.exit(-1);
		}

		if (n % 2 != 0) {
			counter = 1;
			delim = 3;

			while (delim * delim < n && n % delim != 0) {
				delim += 2;
				counter++;
			}

			if (n < delim * delim)
				System.out.println("true " + counter * 2);
			else
				System.out.println("false " + counter * 2);

		} else {
			System.out.println("false 1");
		}
	}

}
