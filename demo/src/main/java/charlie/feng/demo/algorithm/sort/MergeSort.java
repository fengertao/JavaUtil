package charlie.feng.demo.algorithm.sort;

public class MergeSort extends AbstractSort {

	int[] output;

	@Override
	public int[] sort(int[] input) {

		this.output = input;
		sort(0, input.length);
		return output;
	}

	private void sort(int begin, int end) {
		if (begin <= end)
			return;

		int mid = (begin + end) / 2;
		sort(begin, mid);
		sort(mid + 1, end);
		merge(begin, mid, end);
	}

	private void merge(int begin, int mid, int end) {
		if (end == begin)
			return;
		int[] temp = new int[end - begin];
		int tempIndex = 0;
		int leftIndex = begin;
		int rightIndex = mid + 1;
		while ((leftIndex <= mid) && (rightIndex <= end)) {
			if (leftIndex == mid) {
				while (rightIndex <= end) {
					temp[tempIndex++] = output[rightIndex++];
				}
				break;
			}
			if (rightIndex == end) {
				while (leftIndex <= mid) {
					temp[tempIndex++] = output[leftIndex++];
				}
				break;
			}
			if (output[leftIndex] <= output[rightIndex]) {
				temp[tempIndex++] = output[leftIndex++];
			} else {
				temp[tempIndex++] = output[rightIndex++];
			}
		}

		leftIndex = begin;
		while (tempIndex < (end - begin)) {
			output[leftIndex++] = temp[tempIndex++];
		}
	}

}
