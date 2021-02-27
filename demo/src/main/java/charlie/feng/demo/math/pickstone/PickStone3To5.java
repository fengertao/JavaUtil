package charlie.feng.demo.math.pickstone;

import java.util.List;

public class PickStone3To5 extends AbstractPickStone2Pile{
    final List<Integer> legalStones = List.of(3,4,5);

    @Override
    List<Integer> getLegalStones(int i, int j, int pileIndex) {
        return legalStones;
    }

    public static void main(String[] args) {
        new PickStone3To5().play();
    }
}
