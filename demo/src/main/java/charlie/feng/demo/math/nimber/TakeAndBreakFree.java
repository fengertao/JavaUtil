package charlie.feng.demo.math.nimber;

import java.util.HashSet;
import java.util.Set;

/**
 * 多堆石子各自排成一排，每次可以取1枚及以上石子，可以分开石堆.
 */
public class TakeAndBreakFree extends AbstractTakeAndBreak {

    public static void main(String[] args) {
        new TakeAndBreakFree().play();
    }

    @Override
    public Set<Integer> getCandidateNimbers(int n) {
        Set<Integer> stacks = new HashSet<>();
        if (n == 1) {
            stacks.add(nim[0]);
        } else if (n == 2) {
            stacks.add(nim[0]);
            stacks.add(nim[1]);
        } else {
            for (int i = 0; i <= n - 1; i++) {
                for (int j = 0; j < n - 1 - i; j++) {
                    stacks.add(nim[i] ^ nim[j]);
                }
            }
        }
        return stacks;
    }

}
