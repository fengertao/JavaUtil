package charlie.feng.demo.math.pickstone;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 在任一堆取任意多个石子，最后一个取的**输**
 */
public class PickStoneAnyStoneLastLose extends AbstractPickStone2Pile{

    public PickStoneAnyStoneLastLose() {
        super(false);
    }

    @Override
    List<ImmutablePair<Integer, Integer>> getLegalMoves(int i, int j) {
        List<ImmutablePair<Integer, Integer>> moves = new ArrayList<>();
        IntStream.rangeClosed(1,i).forEach(n -> moves.add(ImmutablePair.of(n, 0)));
        IntStream.rangeClosed(1,j).forEach(n -> moves.add(ImmutablePair.of(0, n)));
        return moves;
    }

    public static void main(String[] args) {
        new PickStoneAnyStoneLastLose().play();
    }
}
