package charlie.feng.demo.math.nimber;

import java.util.HashSet;
import java.util.Set;

public class TakeAndBreak37 extends AbstractTakeAndBreak {

    public static void main(String[] args) {
        new TakeAndBreak37().play();
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
            stacks.add(nim[n - 1]);
            stacks.add(nim[n - 2]);
            for (int i = 1; i <= n / 2; i++) {
                stacks.add(nim[i] ^ nim[n - 1 - i]);
                stacks.add(nim[i] ^ nim[n - 2 - i]);
            }
        }
        return stacks;
    }

}
