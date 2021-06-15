package ex04;

import java.util.Scanner;

public class Program {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        int[] lettersCount;
        char[] lettersArray;
        int uniqueLetters = 1;

        lettersCount = new int[65535];
        lettersArray = str.toCharArray();

        for (int i = 0; i < str.length(); i++) {
            lettersCount[lettersArray[i]]++;
            if (lettersCount[lettersArray[i]] == 1)
                uniqueLetters++;
        }

        makePair(lettersArray, uniqueLetters, str.length());
    }

    private static void makePair(char[] lettersArray, int uniqueLetters, int arrayLen) {
        char[] key = new char[uniqueLetters];
        int[] value = new int[uniqueLetters];
        int counter = 0, pos;
        char tmpKey;
        int tmpValue;

        for (int i = 0; i < arrayLen; i++) {
            if ((pos = findKey(key, lettersArray[i], uniqueLetters)) == -1) {
                key[counter] = lettersArray[i];
                value[counter]++;
                counter++;
            } else {
                value[pos]++;
            }
        }

        for (int j = 1; j < uniqueLetters; j++) {
            for (int i = 0; i < uniqueLetters - j; i++) {
                if (!isGreater(key[i], value[i], key[i + 1], value[i + 1])) {
                    tmpKey = key[i];
                    key[i] = key[i + 1];
                    key[i + 1] = tmpKey;
                    tmpValue = value[i];
                    value[i] = value[i + 1];
                    value[i + 1] = tmpValue;
                }
            }
        }

        printResult(key, value);
    }

    private static int findKey(char[] key, char c, int arrayLen) {
        for (int i = 0; i < arrayLen; i++) {
            if (key[i] == c) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isGreater(char key, int value, char key1, int value1) {
        if (value == value1) {
            return key > key1;
        } else {
            return value > value1;
        }
    }

    private static void printResult(char[] key, int[] value) {
        int width = 10, height = 12, grid;
        String[] result = new String[height];

        for (int i = 0; i < height; i++) {
            result[i] = "";
        }

        for (int j = 0; j < width; j++) {
                result[height - 1] += "   " + key[j];

                grid = gridAmount(value[0], value[j], height);
                for (int k = 1; k <= grid; k++) {
                    result[height - k - 1] += "   #";
                }

                result[height - grid - 2] += numberOfSpaces(value[j]) + value[j];
        }

        for (int i = 0; i < height; i++) {
            System.out.println(result[i]);
        }
    }

    private static int gridAmount(int referValue, int value, int height) {
        return (int)((float)value / (float)referValue * (height - 2));
    }

    private static String numberOfSpaces(int num) {
        String str = "";
        int len = 0;

        while (num != 0) {
            num /= 10;
            len++;
        }

        len = 4 - len;
        while (len-- != 0) {
            str += " ";
        }
        return str;
    }
}
