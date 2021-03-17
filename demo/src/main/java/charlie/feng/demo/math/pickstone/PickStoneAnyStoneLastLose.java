package charlie.feng.demo.math.pickstone;

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
    List<Integer> getLegalStones(int i, int j, int pileIndex) {
        int max = (pileIndex == 0) ? i : j;
        return IntStream.rangeClosed(1,max).boxed().collect(Collectors.toList());

    }

    public static void main(String[] args) {
        new PickStoneAnyStoneLastLose().play();
    }
}
