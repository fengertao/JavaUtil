package charlie.feng.game.sudoku;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

// There are 2 number have same candidate BiHiddenCell(*), so those 2 hidden cell cannot support other number.
// *: the position of those 2 hidden cell not as strict as "BiHiddenCellExclude". 
// In the latter case, those cells must be in same line or same column.
// while in this method, in same block is OK.
// And the usage are different. in the latter case, it is used to exclude those numbers from other cells.
// while in this method, it is used to exclude other numbers for those cells.

public class MethodBiHiddenNumberExclusive implements IMethod {

	public void apply(Matrix matrix) {
		for (int i = 0; i < 9; i++) {
			Row row = matrix.rows[i];
			checkBiHiddenNumber(row);
			Column column = matrix.columns[i];
			checkBiHiddenNumber(column);
			Block block = matrix.blocks[i];
			checkBiHiddenNumber(block);
		}
	}

	public void checkBiHiddenNumber(Group group) {
		Cell[] cells = group.cells;
		Set<Integer> k1PossibleOffsetSet = new HashSet<Integer>();
		Set<Integer> k2PossibleOffsetSet = new HashSet<Integer>();
		for (int k1 = 1; k1 <= 9; k1++) {
			if (group.valueBirthed(k1))
				continue;
			group.getPossibleOffset(k1, k1PossibleOffsetSet);
			if (k1PossibleOffsetSet.size() != 2)
				continue;
			for (int k2 = 1; k2 <= 9; k2++) {
				if (k2 == k1)
					continue;
				if (group.valueBirthed(k2))
					continue;
				group.getPossibleOffset(k2, k2PossibleOffsetSet);
				if (k2PossibleOffsetSet.size() != 2)
					continue;

				if (k1PossibleOffsetSet.equals(k2PossibleOffsetSet)) {
					// BiHiddenNumberDetected
					for (Iterator<Integer> offsetSet = k1PossibleOffsetSet
							.iterator(); offsetSet.hasNext();) {
						int offset = offsetSet.next();
						for (int kOther = 1; kOther <= 9; kOther++) {
							if ((kOther == k1) || (kOther == k2)) {
								continue;
							}
							cells[offset].candidates[kOther - 1].setValue(false);
						}
					}
				}
			}
		}

	}

}
