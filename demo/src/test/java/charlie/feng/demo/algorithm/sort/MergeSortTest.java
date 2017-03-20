package charlie.feng.demo.algorithm.sort;

public class MergeSortTest extends AbstractSortTest {

    @Override
    protected AbstractSort getSort() {
        return new MergeSort();
    }

}
