package charlie.feng.game.sudoku;

// This the the only cell in group can use this number. other cell have exclude the possible of this number
public class MethodTheOnlyCellSupportNumber implements IMethod {
    public void apply(Matrix matrix) {
        for (int i = 0; i < 9; i++) {
            Row row = matrix.rows[i];
            checkTheOnlyCellContainsNumber(row);
            Column column = matrix.columns[i];
            checkTheOnlyCellContainsNumber(column);
            Block block = matrix.blocks[i];
            checkTheOnlyCellContainsNumber(block);
        }
    }

    public void checkTheOnlyCellContainsNumber(Group group) {
        for (int k = 0; k < 9; k++) {        //k stand for candidate number
            boolean alreadyFound = false;
            int cellsContainsCandidate = 0;
            Cell cellContainsCandidate = null;

            for (int i = 0; i < 9; i++) {    //i stand for cells iteratior
                if ((group.cells[i].value != null) && (group.cells[i].value.intValue() == k + 1)) {
                    alreadyFound = true;
                    break;
                }
                if (group.cells[i].candidates[k].getValue()) {
                    cellsContainsCandidate++;
                    cellContainsCandidate = group.cells[i];
                }
            }
            if (alreadyFound) continue;
            if (cellsContainsCandidate == 1) {
                cellContainsCandidate.birth(k + 1, "TheOnlyCellSupportNumber");
            }
        }
    }


}
