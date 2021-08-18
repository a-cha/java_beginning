import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Pattern;

public class Program {
	public static void main(String[] args) throws FileNotFoundException {
		List<String> wordsListFromFile1 = wordsListFromFile(args[0]);
		List<String> wordsListFromFile2 = wordsListFromFile(args[1]);

		SortedSet<String> wordsSet = new TreeSet<>();
		wordsSet.addAll(wordsListFromFile1);
		wordsSet.addAll(wordsListFromFile2);
		List<String> wordsList = new LinkedList<>(wordsSet);

		List<Integer> freqFile1 = frequencyCounter(wordsList, wordsListFromFile1);
		List<Integer> freqFile2 = frequencyCounter(wordsList, wordsListFromFile2);

		System.out.println(sumOfSquares(freqFile1, freqFile2) / (
						Math.sqrt(sumOfSquares(freqFile1, freqFile1)) *
						Math.sqrt(sumOfSquares(freqFile2, freqFile2))
				)
		);
	}

	private static Integer sumOfSquares(List<Integer> array1, List<Integer> array2) {
		int sum = 0;
		for (int i = 0; i < array1.size(); i++) {
			sum += array1.get(i) * array2.get(i);
		}
		return sum;
	}

	private static List<Integer> frequencyCounter(List<String> wordsList, List<String> wordsListFromFile1) {
		Integer[] emptyArray = new Integer[wordsList.size()];
		for (int i = 0; i < wordsList.size(); i++) {
			emptyArray[i] = 0;
		}
		List<Integer> frequency = new ArrayList<>(Arrays.asList(emptyArray));
		for (String word : wordsListFromFile1) {
			frequency.set(wordsList.indexOf(word), frequency.get(wordsList.indexOf(word)) + 1);
		}
		return frequency;
	}

	private static List<String> wordsListFromFile(String fileName) throws FileNotFoundException {
		List<String> wordsSet = new LinkedList<>();
		Scanner scanFile = new Scanner(new File(fileName));
		scanFile.useDelimiter(Pattern.compile("[ ,.?;:!/()\\n]"));
		while (scanFile.hasNext()) {
			String str = scanFile.next().toLowerCase(Locale.ROOT);
			if (str.length() > 0) {
				wordsSet.add(str);
			}
		}
		return wordsSet;
	}

}
