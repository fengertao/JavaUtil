package charlie.feng.game.sudoku;

import java.util.Set;

public class Block extends Group{
	
	int blockR;
	int blockC;
	SubGroup[] subRows;
	SubGroup[] subColumns;
	
	public Block(Matrix matrix, int no) {
		this.matrix = matrix;
		this.id = no;
		cells = new Cell[9];
		subRows = new SubRow[]{new SubRow(),new SubRow(),new SubRow()};
		subColumns = new SubColumn[]{new SubColumn(),new SubColumn(),new SubColumn()};
		
		for (int i = 0; i < 3; i++) {
			subRows[i].cells = new Cell[3];
			subColumns[i].cells = new Cell[3];
		}
		
		blockR = no/3;
		blockC = no%3;
		for (int i = 0; i < 9; i++) {
			int offsetR = i/3;
			int offsetC = i%3;
			Cell currentCell = matrix.cells[blockR*3+offsetR][blockC*3+offsetC];
			cells[i] = currentCell;
			subRows[offsetR].cells[offsetC] = currentCell;
			subColumns[offsetC].cells[offsetR] = currentCell;
		}
	}

	public Set<Integer> getPossibleSubGroup(int k, boolean isRow, Set<Integer> resultSet) {
		resultSet.clear();
		for (int i = 0; i < 3; i++) {
			SubGroup subGroup = isRow?subRows[i]:subColumns[i];
			if (subGroup.supportNumber(k)) {resultSet.add(i);}
		}
		return resultSet;
	}

}
