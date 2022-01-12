package charlie.feng.demo.math.nimber;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public abstract class AbstractTakeAndBreak extends AbstractPickStone {

    public int calculateNim(int i) {
        List<List<Integer>> legalReminders = getLegalReminders(i);
        Set<Integer> candidateNims = new TreeSet<>();
        for (List<Integer> reminders : legalReminders) {
            candidateNims.add(reminders.stream().reduce(0, (result, stones) -> result ^ nim[stones]));
        }
        return mex(candidateNims);
    }

    public abstract List<List<Integer>> getLegalReminders(int i);
}
