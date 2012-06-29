package charlie.feng.game.sudoku;

public class Column extends Group{
	
	public Column(Matrix matrix, int colId) {
		this.matrix = matrix;
		this.id = colId;
		cells = new Cell[9];
		subGroup = new SubColumn[]{new SubColumn(),new SubColumn(),new SubColumn()};
		for (int i = 0; i < 3; i++) {
			subGroup[i].cells = new Cell[3];
		}
		for (int i = 0; i < 9; i++) {
			Cell currentCell = matrix.cells[i][colId];
			cells[i] = currentCell;
			subGroup[i/3].cells[i%3] = currentCell;
		}
	}

}
