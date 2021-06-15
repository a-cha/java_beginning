package ex00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Program {
	public static void main(String[] args) throws IOException {
		Scanner				scanner = new Scanner(System.in);
		Map<String, String> signaturesMap = new HashMap<>();
		String				filePath;
		FileOutputStream	resultFile = new FileOutputStream("/Users/sadolph/Desktop/Day02/src/ex00/result.txt", false);

		fillSignaturesMap(signaturesMap);

		while (true) {
			filePath = scanner.nextLine();
			if (filePath.equals("42")) {
				resultFile.close();
				return;
			}
			System.out.println("PROCESSED");
			checkFileSignature(signaturesMap, filePath, resultFile);
		}

	}

	private static void checkFileSignature(Map<String, String> signaturesMap, String filePath, FileOutputStream resultFile) throws IOException {
		String				extension, bytes = "";
		FileInputStream		fileInputStream = new FileInputStream(filePath);

		for (int i = 0; i < 8; i++) {
			int b = fileInputStream.read();
			bytes += Integer.toHexString(b).toUpperCase(Locale.ROOT);
			if (!(extension = findSignatureInMap(bytes, signaturesMap)).equals("Not found")) {
				resultFile.write((extension + "\n").getBytes(StandardCharsets.UTF_8));
			}
			bytes += " ";
		}
	}

	private static void fillSignaturesMap(Map<String, String> signaturesMap) throws IOException {
		FileInputStream	fileInputStream;
		String[]		signaturesArray;
		String			signaturesStr = "";
		int				c;

		fileInputStream = new FileInputStream("/Users/sadolph/Desktop/Day02/src/ex00/signatures.txt");

		while ((c = fileInputStream.read()) != -1) {
			signaturesStr += (char)c;
		}

		signaturesArray = signaturesStr.split("\n");
		for (String str : signaturesArray) {
			String[] tmp = str.split(", ");
			signaturesMap.put(tmp[0], tmp[1]);
		}

		fileInputStream.close();
	}

	private static String findSignatureInMap(String bytes, Map<String, String> signaturesMap) {
		String[]	keys = signaturesMap.keySet().toArray(new String[0]);
		String[]	values = signaturesMap.values().toArray(new String[0]);
		int			i = 0;

		for (String value : values) {
			if (value.equals(bytes)) {
				return keys[i];
			}
			i++;
		}
		return "Not found";
	}
}
