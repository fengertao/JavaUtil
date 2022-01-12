package charlie.feng.demo.math.nimber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 多堆石子各自排成一排，每次可以取1枚及以上石子，可以分开石堆.
 */
public class TakeAndBreakFree extends AbstractTakeAndBreak {

    public static void main(String[] args) {
        new TakeAndBreakFree().play();
    }

    @Override
    public List<List<Integer>> getLegalReminders(int n) {
        List<List<Integer>> stacks = new ArrayList<>();
        if (n == 1) {
            stacks.add(Collections.singletonList(0));
        } else if (n == 2) {
            stacks.add(Collections.singletonList(0));
            stacks.add(Collections.singletonList(1));
        } else {
            for (int i = 0; i <= n - 1; i++) {
                for (int j = 0; j < n - 1 - i; j++) {
                    stacks.add(List.of(i, j));
                }
            }
        }
        return stacks;
    }

}
