package charlie.feng.demo.math.pickstone;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 在任一堆取任意多个石子，最后一个取的**输**
 */
public class PickStone3PileAnyStoneLastLose extends AbstractPickStone3Pile {

    public PickStone3PileAnyStoneLastLose() {
        super(true);
    }

    public static void main(String[] args) {
        new PickStone3PileAnyStoneLastLose().play();
    }

    @Override
    List<Integer> getLegalStones(int i, int j, int k, int pileIndex) {
        int max = switch (pileIndex) {
            case 0 -> i;
            case 1 -> j;
            case 2 -> k;
            default -> 0;
        };
        return IntStream.rangeClosed(1, max).boxed().collect(Collectors.toList());

    }
}
