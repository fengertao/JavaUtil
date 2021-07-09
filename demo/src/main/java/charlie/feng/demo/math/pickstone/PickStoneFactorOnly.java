package charlie.feng.demo.math.pickstone;

import charlie.feng.demo.math.MathUtil;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 有四堆石子，分别是2，4，8，16个，两个人轮流取，每次只能从一堆中取，取的数目是这堆的约数，取最后一个的获胜，先手和后手谁有必胜策略
 */

public class PickStoneFactorOnly extends AbstractPickStone2Pile {

    @SuppressWarnings("unchecked")
    static final List<Integer>[] factors = new ArrayList[MAX_STONES + 1];

    static {
        factors[0] = new ArrayList<>();
        for (int i = 1; i < MAX_STONES + 1; i++) {
            factors[i] = MathUtil.getAllFactors(i);
        }
    }

    public PickStoneFactorOnly() {
        super(true);
    }

    public static void main(String[] args) {
        new PickStoneFactorOnly().play();
    }

    @Override
    List<ImmutablePair<Integer, Integer>> getLegalMoves(int i, int j) {
        List<ImmutablePair<Integer, Integer>> moves = new ArrayList<>();
        factors[i].forEach(n -> moves.add(ImmutablePair.of(n, 0)));
        factors[j].forEach(n -> moves.add(ImmutablePair.of(0, n)));
        return moves;
    }
}
