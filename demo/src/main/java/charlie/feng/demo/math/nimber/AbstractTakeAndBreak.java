package charlie.feng.demo.math.nimber;

import java.util.Set;

public abstract class AbstractTakeAndBreak extends AbstractPickStone {

    public int calculateNim(int i) {
        Set<Integer> candidateNimbers = getCandidateNimbers(i);
        return mex(candidateNimbers);

    }

    public abstract Set<Integer> getCandidateNimbers(int i);
}
