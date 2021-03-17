package charlie.feng.demo.math.pickstone;

import charlie.feng.demo.math.MathUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 有四堆石子，分别是2，4，8，16个，两个人轮流取，每次只能从一堆中取，取的数目是这堆的约数，取最后一个的获胜，先手和后手谁有必胜策略
 */

public class PickStone3FactorOnly extends AbstractPickStone3Pile {

    @SuppressWarnings("unchecked")
    static final List<Integer>[] factors = new ArrayList[MAX_STONES + 1];

    static {
        factors[0] = new ArrayList<>();
        for (int i = 1; i < MAX_STONES + 1; i++) {
            factors[i] = MathUtil.getAllFactors(i);
        }
    }

    public PickStone3FactorOnly() {
        super(true);
    }

    public static void main(String[] args) {
        new PickStone3FactorOnly().play();
    }

    @Override
    List<Integer> getLegalStones(int i, int j, int k, int pileIndex) {
        return switch (pileIndex) {
            case 0 -> factors[i];
            case 1 -> factors[j];
            case 2 -> factors[k];
            default -> null;
        };
    }
}
