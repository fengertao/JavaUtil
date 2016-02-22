package charlie.feng.demo.algorithm.sort;

public class QuickSort<T extends Comparable<T>> extends AbstractSort<T> {

	private void swap(T[] input, int i, int j) {
		T iTemp = input[i];
		input[i] = input[j];
		input[j] = iTemp;
	}

	@Override
	public T[] sort(T[] input) {
		if ((input == null) || (input.length == 0)) {
			return input;
		}
		return sort(input, 0, input.length - 1);
	}

	private T[] sort(T[] input, int begin, int end) {
		if (begin >= end)
			return input;
		T monitorValue = input[end];
		int leftIndex = begin;
		int rightIndex = end - 1;
		while (leftIndex < rightIndex) {
			while ((leftIndex < rightIndex) && (input[leftIndex].compareTo( monitorValue)<0)) {
				leftIndex++;
			}
			while ((leftIndex < rightIndex) && (input[rightIndex].compareTo(monitorValue)>=0)) {
				rightIndex--;
			}
			swap(input, leftIndex, rightIndex);
		}
		if (input[leftIndex].compareTo(input[end])>=0) {
			swap(input, leftIndex, end);
		} else {
			leftIndex++;
		}
		sort(input, begin, leftIndex - 1);
		sort(input, leftIndex + 1, end);

		return input;
	}

}
