package charlie.feng.demo.math.nimber;

import charlie.feng.demo.math.MathUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 一堆石子，每次只可以拿出因数个石子。
 */
public class SubtractFactorOnly extends AbstractSubtraction {

    static final List<Integer>[] factors = new ArrayList[maxStones + 1];

    static {
        factors[0] = new ArrayList<>();
        for (int i = 1; i < maxStones + 1; i++) {
            factors[i] = MathUtil.getAllFactors(i);
        }
    }

    public static void main(String[] args) {
        new SubtractFactorOnly().play();
    }

    @Override
    List<Integer> getLegalMoves(int i) {
        return factors[i];
    }
}
