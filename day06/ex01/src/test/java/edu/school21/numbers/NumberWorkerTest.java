package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {

	@ParameterizedTest
	@ValueSource (ints = {2, 3, 5, 113})
	public void isPrimeForPrimes(int primeNum) {
		assertTrue(new NumberWorker().isPrime(primeNum));
	}

	@ParameterizedTest
	@ValueSource (ints = {4, 44, 169})
	public void isPrimeForNotPrimes(int notPrimeNum) {
		assertFalse(new NumberWorker().isPrime(notPrimeNum));
	}

	@ParameterizedTest
	@ValueSource (ints = {-3, 0, 1})
	public void isPrimeForIncorrectNumbers(int incorrectNum) {
		assertThrows(NumberWorker.IllegalNumberException.class, () -> new NumberWorker().isPrime(incorrectNum));
	}

	@ParameterizedTest
	@CsvFileSource (resources = "/data.csv", numLinesToSkip = 1)
	public void digitsSum(int input, int expected) {
		assertEquals(expected, new NumberWorker().digitsSum(input));
	}
}
