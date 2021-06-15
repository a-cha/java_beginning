package ex02;

import java.util.Random;

public class Main {
	static public void main(String[] args) throws InterruptedException {
		validateArgs(args);

		int arraySize = Integer.parseInt(args[0].replaceAll("[^0-9]", ""));
		int threadsCount = Integer.parseInt(args[1].replaceAll("[^0-9]", ""));
		Random random = new Random();
		Thread[] threads = new Thread[threadsCount];
		int[] array = new int[arraySize];
		IntHolder[] result = new IntHolder[threadsCount];

		for (int i = 0; i < arraySize; i++) {
			array[i] = random.nextInt(500);
		}

		for (int i = 0; i < threadsCount; i++) {
			result[i] = new IntHolder();
		}

		int range = arraySize / threadsCount;
		for (int i = 0; i < threadsCount - (arraySize % threadsCount != 0 ? 1 : 0); i++) {
			threads[i] = new Thread(new Counter(array, i * range, (i + 1) * range - 1, result[i]));
			threads[i].setName(Integer.toString(i));
			threads[i].start();
		}
		if (arraySize % threadsCount != 0) {
			threads[threadsCount - 1] = new Thread(new Counter(array, (threadsCount - 1) * range, arraySize - 1, result[threadsCount - 1]));
			threads[threadsCount - 1].setName(Integer.toString(threadsCount - 1));
			threads[threadsCount - 1].start();
		}

		for (int i = 0; i < threadsCount; i++) {
			threads[i].join();
		}

		int resSum = 0;
		for (int i = 0; i < threadsCount; i++) {
			resSum += result[i].num;
		}

		System.out.printf("Sum by threads: %d\n", resSum);
	}

	private static void validateArgs(String[] args) {
		if (args.length != 2) {
			System.out.println("Need 2 arguments!");
			System.exit(-1);
		}
		if (!(args[0].contains("--arraySize=")) || !(args[1].contains("--threadsCount="))) {
			System.out.println("Syntax arguments error. \nFormat: --arraySize=*number of elements in array* --threadsCount=*number of threads*");
			System.exit(-1);
		}
		String size = args[0].replaceAll("[^0-9]", "");
		String count = args[1].replaceAll("[^0-9]", "");
		if (size.equals("") || count.equals("")) {
			System.out.println("Error valid arguments!");
			System.exit(-1);
		}
	}

	public static class IntHolder {
		public int num;

		public IntHolder() {
			this.num = 0;
		}
	}

	static class Counter implements Runnable {
		int[]		array;
		int			startIndex;
		int			endIndex;
		IntHolder result;

		public Counter(int[] array, int startIndex, int endIndex, IntHolder result) {
			this.array = array;
			this.startIndex = startIndex;
			this.endIndex = endIndex;
			this.result = result;
		}

		@Override
		public void run() {
			int sum = 0;
			for (int i = startIndex; i <= endIndex; i++) {
				sum += array[i];
			}
			result.num = sum;
			System.out.printf("Thread %s: from %d to %d sum is %d\n", Thread.currentThread().getName(), startIndex, endIndex, result.num);
		}
	}

}
