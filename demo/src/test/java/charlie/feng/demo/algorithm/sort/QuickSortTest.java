package charlie.feng.demo.algorithm.sort;

import org.junit.Assert;
import org.junit.Test;

public class QuickSortTest {

	@Test
	public void test1() {
		int[] input = new int[] { 14, 6, 8, 9, 27, 10, 18, 4, 18, 22, 9, 11 };
		int[] correctOutput = new int[] { 4, 6, 8, 9, 9, 11, 11, 14, 18, 18, 22, 27 };

		AbstractSort sort = new QuickSort();
		int[] output = sort.sort(input);
		print(output);

		Assert.assertTrue(isValidated(output, correctOutput));
	}

	@Test
	public void testRandom() {
		int LENGTH = 50;
		int RANGE = 100;
		int[] input = new int[LENGTH];
		for (int i = 0; i < LENGTH; i++) {
			input[i] = (int) Math.round(Math.random() * RANGE);
		}
		print(input);

		AbstractSort sort = new QuickSort();
		int[] output = sort.sort(input);
		print(output);
		System.out.println("");

		Assert.assertTrue(isOrdered(output));
	}

	private void print(int[] output) {
		for (int i : output) {
			System.out.print(i + " ");
		}
		System.out.println("");
	}

	private boolean isValidated(int[] output, int[] correctOutput) {
		if ((output.length) != correctOutput.length)
			return false;
		for (int i = 0; i < output.length; i++) {
			if (output[i] != correctOutput[i]) {
				return false;
			}
		}
		return true;
	};

	private boolean isOrdered(int[] output) {
		for (int i = 1; i < output.length; i++) {
			if (output[i] < output[i - 1]) {
				return false;
			}
		}
		return true;
	}

}
