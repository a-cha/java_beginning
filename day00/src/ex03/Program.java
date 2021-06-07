package ex03;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner	scanner = new Scanner(System.in);
		long   	marks = 0;
		int   	tmp = 10, tmp1;
		String 	week;
		int		i = 1;

		for (; i <= 18; i++) {
			week = scanner.next();
			if (week.equals("42")) {
				break;
			}
			if (!week.equals("Week")) {
				errorExit();
			}
			if (i != scanner.nextInt()) {
				errorExit();
			}
			for (int j = 0; j < 5; j++) {
				if (scanner.hasNextInt() && (tmp1 = scanner.nextInt()) < tmp)
						tmp = tmp1;
			}
			long t = power(10, i);
			t = tmp * t;
			marks = t + marks;
			tmp = 10;
		}
		if (i == 19) {
			week = scanner.next();
			if (!week.equals("42")) {
				errorExit();
			}
		}
		printResult(marks);
	}

	private static void printResult(long marks) {
		int		week = 1;
		long	m;

		while (marks != 0) {
			System.out.printf("Week %d ", week);
			m = marks % 10;
			while (m != 0) {
				System.out.print("=");
				m--;
			}
			System.out.println(">");
			marks /= 10;
			week++;
		}
	}

	private static long power(long num, int pow) {
		if (pow == 0 || pow == 1)
			return 1;

		long res = num;
		while (--pow > 1) {
			res *= num;
		}
		return res;
	}

	static void errorExit() {
		System.err.print("IllegalArgument");
		System.exit(-1);
	}

}
