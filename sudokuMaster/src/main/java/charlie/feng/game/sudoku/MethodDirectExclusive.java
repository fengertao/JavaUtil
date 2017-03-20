package charlie.feng.game.sudoku;

//If we know the number of this cell, his Neighboor cell cannot have same number.
public class MethodDirectExclusive implements IMethod {
    public void apply(Matrix matrix) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Cell cell = matrix.cells[r][c];
                if (cell.value != null) {
                    cell.noticeNeighboorsAboutBirth();
                }
            }
        }
    }

}
