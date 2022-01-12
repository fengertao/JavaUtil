package charlie.feng.demo.math.nimber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TakeAndBreak37 extends AbstractTakeAndBreak {

    public static void main(String[] args) {
        new TakeAndBreak37().play();
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
            stacks.add(Collections.singletonList(n - 1));
            stacks.add(Collections.singletonList(n - 2));
            for (int i = 1; i <= n / 2; i++) {
                stacks.add(List.of(i, n - 1 - i));
                stacks.add(List.of(i, n - 2 - i));
            }
        }
        return stacks;
    }

}
