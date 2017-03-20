package charlie.feng.game.sudoku;

public class Row extends Group {

    public Row(Matrix matrix, int rowid) {
        this.matrix = matrix;
        this.id = rowid;
        cells = new Cell[9];
        subGroup = new SubRow[]{new SubRow(), new SubRow(), new SubRow()};
        for (int i = 0; i < 3; i++) {
            subGroup[i].cells = new Cell[3];
        }
        for (int i = 0; i < 9; i++) {
            Cell currentCell = matrix.cells[rowid][i];
            cells[i] = currentCell;
            subGroup[i / 3].cells[i % 3] = currentCell;
        }
    }

}
