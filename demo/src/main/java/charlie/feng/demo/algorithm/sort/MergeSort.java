package charlie.feng.demo.algorithm.sort;

import java.lang.reflect.Array;

public class MergeSort<T extends Comparable<T>> extends AbstractSort<T> {

    T[] output;
    Class<T> componentType;

    @Override
    public T[] sort(T[] input) {
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

    interface Display {
        int show(String s1, String s2);
    }
    private void merge(int begin, int mid, int end) {
        Display display = String::indexOf;
        if (end == begin)
            return;

        T[] temp = (T[]) Array.newInstance(componentType, end - begin);
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
            if (output[leftIndex].compareTo(output[rightIndex]) <= 0) {
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
