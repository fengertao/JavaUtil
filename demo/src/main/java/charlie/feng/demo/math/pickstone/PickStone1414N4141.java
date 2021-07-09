package charlie.feng.demo.math.pickstone;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 有两堆石子，分别是1414,4141个，两个人轮流取，每次可以从一堆中取任意数目的石子，或者从两堆中取同样数目的石子，或相差一个的石子，取最后一个的获胜，先手和后手谁有必胜策略
 */

public class PickStone1414N4141 extends AbstractPickStone2Pile {

    public PickStone1414N4141() {
        super(true);
    }

    public static void main(String[] args) {
        new PickStone1414N4141().play();
    }

    @Override
    List<ImmutablePair<Integer, Integer>> getLegalMoves(int i, int j) {
        List<ImmutablePair<Integer, Integer>> moves = new ArrayList<>();
        IntStream.rangeClosed(1,i).forEach(n -> moves.add(ImmutablePair.of(n, 0)));
        IntStream.rangeClosed(1,j).forEach(n -> moves.add(ImmutablePair.of(0, n)));
//        factors[i].forEach(n -> moves.add(ImmutablePair.of(n, 0)));
//        factors[j].forEach(n -> moves.add(ImmutablePair.of(0, n)));
        return moves;
    }
}
