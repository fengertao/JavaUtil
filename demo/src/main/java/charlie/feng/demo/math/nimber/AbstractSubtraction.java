package charlie.feng.demo.math.nimber;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class AbstractSubtraction extends AbstractPickStone {

    public int calculateNim(int i) {
        List<Integer> legalStones = getLegalMoves(i);
        Set<Integer> candidateNims = new TreeSet<>();
        for (int m : legalStones) {
            if (i < m) continue;
            candidateNims.add(nim[i - m]);
        }
        return mex(candidateNims);
    }

    abstract List<Integer> getLegalMoves(int i);

}
