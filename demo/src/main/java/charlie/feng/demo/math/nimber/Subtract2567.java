package charlie.feng.demo.math.nimber;

import java.util.List;

public class Subtract2567 extends AbstractSubtraction {
    final List<Integer> legalStones = List.of(2, 5, 6, 7);

    public static void main(String[] args) {
        new Subtract2567().play();
    }

    @Override
    List<Integer> getLegalMoves(int i) {
        return legalStones;
    }
}
