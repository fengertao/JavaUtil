package charlie.feng.game.sudoku;

//no other candidate number in cell.
public class MethodSurplusOne implements IMethod {

    public void apply(Matrix matrix) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                Cell cell = matrix.cells[r][c];
                if ((cell.value != null) && (cell.getNoCandidates() == 1)) {
                    cell.noticeNeighboorsAboutBirth();
                }
            }
        }
    }
}
