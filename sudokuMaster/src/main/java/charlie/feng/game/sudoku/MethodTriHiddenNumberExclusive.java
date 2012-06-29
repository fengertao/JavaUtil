package charlie.feng.game.sudoku;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//Refer to BiHiddenNumberExclusive
public class MethodTriHiddenNumberExclusive implements IMethod {


	public void apply(Matrix matrix) {
		for (int i = 0; i < 9; i++) {
			Row row = matrix.rows[i];
			checkTriHiddenNumber(row);
			Column column = matrix.columns[i];
			checkTriHiddenNumber(column);
			Block block = matrix.blocks[i];
			checkTriHiddenNumber(block);
		}
	}

	public void checkTriHiddenNumber(Group group) {
		Cell[] cells = group.cells;
		Set<Integer> k1PossibleOffsetSet = new HashSet<Integer>();
		Set<Integer> k2PossibleOffsetSet = new HashSet<Integer>();
		Set<Integer> k3PossibleOffsetSet = new HashSet<Integer>();
		Set<Integer> mergedOffsetSet = new HashSet<Integer>(); 

		for (int k1 = 1; k1 <= 9; k1++) {
			if (group.valueBirthed(k1))
				continue;
			group.getPossibleOffset(k1, k1PossibleOffsetSet);
			if (k1PossibleOffsetSet.size() >3) continue;
			for (int k2 = 1; k2 <= 9; k2++) {
				if (k2 == k1)continue;
				if (group.valueBirthed(k2))	continue;
				group.getPossibleOffset(k2, k2PossibleOffsetSet);
				if (k2PossibleOffsetSet.size() > 2) continue;

				for (int k3 = 1; k3 <= 9; k3++) {
					if ((k3 == k1) || (k3 == k2))continue;
					if (group.valueBirthed(k3))	continue;
					group.getPossibleOffset(k3, k3PossibleOffsetSet);
					if (k3PossibleOffsetSet.size() > 3) continue;
					
					mergedOffsetSet.clear();
					mergedOffsetSet.addAll(k1PossibleOffsetSet);
					mergedOffsetSet.addAll(k2PossibleOffsetSet);
					mergedOffsetSet.addAll(k3PossibleOffsetSet);
					
					if (mergedOffsetSet.size()<=3 ) {
						// BiHiddenNumberDetected
						for (Iterator<Integer> offsetSet = mergedOffsetSet
								.iterator(); offsetSet.hasNext();) {
							int offset = offsetSet.next();
							for (int kOther = 1; kOther <= 9; kOther++) {
								if ((kOther == k1) || (kOther == k2) || (kOther == k3)) {
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


}
