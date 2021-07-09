package charlie.feng.demo.math.pickstone;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.List;

public class PickStone3To5 extends AbstractPickStone2Pile {
    final List<Integer> legalStones = List.of(3, 4, 5);
    List<ImmutablePair<Integer, Integer>> legalMoves = List.of(
            ImmutablePair.of(3, 0),
            ImmutablePair.of(4, 0),
            ImmutablePair.of(5, 0),
            ImmutablePair.of(0, 3),
            ImmutablePair.of(0, 4),
            ImmutablePair.of(0, 5));

    public PickStone3To5() {
        super(true);
    }

    @Override
    List<ImmutablePair<Integer, Integer>> getLegalMoves(int i, int j) {
        return legalMoves;
    }

    public static void main(String[] args) {
        new PickStone3To5().play();
    }
}
