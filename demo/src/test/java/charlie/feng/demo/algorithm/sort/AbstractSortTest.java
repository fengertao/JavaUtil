package charlie.feng.demo.algorithm.sort;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class AbstractSortTest<T extends Comparable<T>> {

    public AbstractSortTest() {
        super();
    }

    protected abstract AbstractSort<T> getSort();

    @Test
    public void test1() {
        Integer[] input = new Integer[]{14, 6, 8, 9, 27, 10, 18, 11, 4, 18, 22, 9, 11};
        Integer[] correctOutput = new Integer[]{4, 6, 8, 9, 9, 10, 11, 11, 14, 18, 18, 22, 27};

        AbstractSort<Integer> sort = new QuickSort<>();
        Integer[] output = sort.sort(input);
        print(output);
        Assertions.assertTrue(isValidated(output, correctOutput));
    }

    @Test
    public void testRandom() {
        int LENGTH = 50;
        int RANGE = 100;
        int LOOPS = 100;
        for (int loop = 0; loop < LOOPS; loop++) {
            Integer[] input = new Integer[LENGTH];
            for (int i = 0; i < LENGTH; i++) {
                input[i] = (int) Math.round(Math.random() * RANGE);
            }
            print(input);

            AbstractSort<Integer> sort = new QuickSort<>();
            Integer[] output = sort.sort(input);
            print(output);

            Assertions.assertTrue(isOrdered(output));

        }
    }

    private void print(Object[] output) {
        //uncomment me during test.
        //		for (Object i : output) {
        //			System.out.print(i + " ");
        //		}
        //		System.out.println("");
    }

    private boolean isValidated(Integer[] output, Integer[] correctOutput) {
        if ((output.length) != correctOutput.length)
            return false;
        for (int i = 0; i < output.length; i++) {
            if (!output[i].equals(correctOutput[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean isOrdered(Integer[] output) {
        for (int i = 1; i < output.length; i++) {
            if (output[i] < output[i - 1]) {
                return false;
            }
        }
        return true;
    }

}
