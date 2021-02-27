package charlie.feng.demo.math;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MathUtil {

    public static List<Integer> getAllFactors(int n) {
        List<Integer> factors = new ArrayList<>();
        int upperlimit = (int) (Math.sqrt(n));
        for (int i = 1; i <= upperlimit; i += 1) {
            if (n % i == 0) {
                factors.add(i);
                if (i != n / i) {
                    factors.add(n / i);
                }
            }
        }
        Collections.sort(factors);
        return factors;
    }
}
