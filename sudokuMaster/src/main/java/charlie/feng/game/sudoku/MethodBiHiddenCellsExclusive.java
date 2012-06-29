package charlie.feng.game.sudoku;

//BiHiddenCell: A number can existing in one of two cell(in same row or same col), while unconfirm which of them.
//TriHiddenCell: A number can existing in one of three cell(in same row or same col),, while unconfirm which of them.
public class MethodBiHiddenCellsExclusive implements IMethod {
	public void apply(Matrix matrix) {
		for (int i = 0; i < 9; i++) {
			Row row = matrix.rows[i];
			checkHiddenCellInRowColumn(matrix, row, true) ;
			Column column = matrix.columns[i];
			checkHiddenCellInRowColumn(matrix, column, false) ;
			Block block = matrix.blocks[i];
			checkHiddenCellInBlock(matrix, block) ;
		}
	}
	
	private void checkHiddenCellInRowColumn(Matrix matrix, Group group, boolean isRow) {
		for (int k = 1; k <= 9; k++) {
			if (group.valueBirthed(k)) {continue;}

			int subGroupCount=0;
			int hiddenSubGroupOffset = 10;

			for (int subgroupNo = 0; subgroupNo < 3; subgroupNo++) {
				if (group.subGroup[subgroupNo].supportNumber(k)) {
					subGroupCount ++;
					hiddenSubGroupOffset = subgroupNo;
				}
			}
			if (subGroupCount == 1) {
				int blockId;
				if (isRow) {
					blockId = group.id/3*3+hiddenSubGroupOffset;
				} else {
					blockId = group.id/3 + hiddenSubGroupOffset*3;
				}
				matrix.blocks[blockId].removeNumber(k, group.subGroup[hiddenSubGroupOffset].cells);
			}
		}
	}

	public void checkHiddenCellInBlock(Matrix matrix, Block block) {
		for (int k = 1; k <= 9; k++) {
			if (block.valueBirthed(k)) {continue;}
			{
				int subGroupCount=0;
				int hiddenSubGroupOffset = 10;
				
				for (int subgroupNo = 0; subgroupNo < 3; subgroupNo++) {
					if (block.subRows[subgroupNo].supportNumber(k)){
						subGroupCount ++;
						hiddenSubGroupOffset = subgroupNo;
					}
				}
				
				if (subGroupCount == 1) {
					matrix.rows[block.blockR*3+hiddenSubGroupOffset].removeNumber(k, block.subRows[hiddenSubGroupOffset].cells);
				}
			}
			{
				int subGroupCount=0;
				int hiddenSubGroupOffset = 10;
				
				for (int subgroupNo = 0; subgroupNo < 3; subgroupNo++) {
					if (block.subColumns[subgroupNo].supportNumber(k)){
						subGroupCount ++;
						hiddenSubGroupOffset = subgroupNo;
					}
				}
				
				if (subGroupCount == 1) {
					matrix.columns[block.blockC*3+hiddenSubGroupOffset].removeNumber(k, block.subColumns[hiddenSubGroupOffset].cells);
				}
			}

		
		}
	}
}
