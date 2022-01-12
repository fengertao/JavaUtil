package charlie.feng.demo.math.nimber;

import java.util.List;

public class Subtract3To5 extends AbstractSubtraction {
    final List<Integer> legalStones = List.of(3, 4, 5);

    public static void main(String[] args) {
        new Subtract3To5().play();
    }

    @Override
    List<Integer> getLegalMoves(int i) {
        return legalStones;
    }
}
