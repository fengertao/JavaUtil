package charlie.feng.game.sudoku;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

//Three cell of same group, each of them have 2~4 candidates numbers left, and their union is 4 numbers
//so we know other cells of this group don't support those 4 candidate numbers.

public class MethodFourSurplusFourExclusive implements IMethod {

	public void apply(Matrix matrix) {
		for (int i = 0; i < 9; i++) {
			Row row = matrix.rows[i];
			checkTriHiddenNumber(row) ;
			Column column = matrix.columns[i];
			checkTriHiddenNumber(column) ;
			Block block = matrix.blocks[i];
			checkTriHiddenNumber(block) ;
		}
	}
		
	public void checkTriHiddenNumber(Group group) {
		Cell[] cells = group.cells;
		Set<Integer> supportNumberSet = new HashSet<Integer>();
		for (int i1=0; i1<9; i1++) {
			if (cells[i1].value != null) continue;
			for (int i2=0; i2<9; i2++) {
				if (cells[i2].value != null) continue;
				if (i2==i1) continue;
				for (int i3 = 0; i3 < 9; i3++) {
					if (cells[i3].value != null) continue;
					if ((i3 == i2) || (i3 == i1)) continue;
					for (int i4 = 0; i4 < 9; i4++) {
						if (cells[i4].value != null) continue;
						if ((i4 == i3) || (i4 == i2) || (i4 == i1)) continue;
					
						supportNumberSet.clear();
						for (int k = 0; k < 9; k++) {
							if (cells[i1].candidates[k].getValue()) { supportNumberSet.add(k+1); continue;}
							if (cells[i2].candidates[k].getValue()) { supportNumberSet.add(k+1); continue;}
							if (cells[i3].candidates[k].getValue()) { supportNumberSet.add(k+1); continue;}
							if (cells[i4].candidates[k].getValue()) { supportNumberSet.add(k+1); continue;}
						}
						
						if (supportNumberSet.size() <= 4) {
							Cell[] excludeCells = new Cell[4];
							excludeCells[0] = cells[i1];
							excludeCells[1] = cells[i2];
							excludeCells[2] = cells[i3];
							excludeCells[3] = cells[i4];
							
							for (Iterator<Integer> iHiddenNumber = supportNumberSet.iterator(); iHiddenNumber.hasNext();) {
								int hiddenNumber =  iHiddenNumber.next();
								group.removeNumber(hiddenNumber, excludeCells);
							}
						}
					}
				}
			}
		}
	}
}
