package charlie.feng.game.sudoku;

//all other 8 cells of group have birthed
//AKA one cell left
public class MethodLastBaby implements IMethod {
    public void apply(Matrix matrix) {
        for (int i = 0; i < 9; i++) {
            Row row = matrix.rows[i];
            checkOneCellLeft(row);
            Column column = matrix.columns[i];
            checkOneCellLeft(column);
            Block block = matrix.blocks[i];
            checkOneCellLeft(block);
        }
    }

    public void checkOneCellLeft(Group group) {
        Cell leftCell = null;
        int counter = 0;
        int summary = 0;
        int expectSummary = 45;  //1+2+..+9=45
        for (int i = 0; i < 9; i++) {
            Cell cell = group.cells[i];
            if (cell.value != null) {
                counter++;
                summary += cell.value.intValue();
            } else {
                leftCell = cell;
            }
        }
        if (counter == 8) {
            leftCell.birth(expectSummary - summary, "MethodOneCellLeft");
        }

    }
}
