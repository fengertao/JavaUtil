package charlie.feng.demo.math.nimber;

import java.util.List;

public class Subtract1To4 extends AbstractSubtraction {
    final List<Integer> legalStones = List.of(1, 2, 3, 4);

    public static void main(String[] args) {
        new Subtract1To4().play();
    }

    @Override
    List<Integer> getLegalMoves(int i) {
        return legalStones;
    }
}
