package charlie.feng.demo.algorithm.sort;

public class QuickSort extends AbstractSort {

	private void swap(int[] input, int i, int j) {
		int iTemp = input[i];
		input[i] = input[j];
		input[j] = iTemp;
	}

	@Override
	public int[] sort(int[] input) {
		if ((input == null) || (input.length == 0)) {
			return input;
		}
		return sort(input, 0, input.length - 1);
	}

	private int[] sort(int[] input, int begin, int end) {
		int monitorValue = input[end];
		int leftIndex = begin;
		int rightIndex = end - 1;
		while (leftIndex < rightIndex) {
			while ((leftIndex < rightIndex) && (input[leftIndex] < monitorValue)) {
				leftIndex++;
			}
			while ((leftIndex < rightIndex) && (input[rightIndex] >= monitorValue)) {
				rightIndex--;
			}
			swap(input, leftIndex, rightIndex);
		}
		if (input[leftIndex] >= input[end])
			swap(input, leftIndex, end);
		else {
			System.out.println(input);
			leftIndex++;
		}
		sort(input, begin, leftIndex - 1);
		sort(input, leftIndex + 1, end);

		return input;
	}

}
