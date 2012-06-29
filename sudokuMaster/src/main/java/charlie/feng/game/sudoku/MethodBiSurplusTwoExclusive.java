package charlie.feng.game.sudoku;


// two cell of same group, each of them have 2 candidate numbers left, and their values same.
// so we know other cells of this group don't support those 2 candidate number.
public class MethodBiSurplusTwoExclusive implements IMethod {

	public void apply(Matrix matrix) {
		for (int i = 0; i < 9; i++) {
			Row row = matrix.rows[i];
			checkBiHiddenNumber(row) ;
			Column column = matrix.columns[i];
			checkBiHiddenNumber(column) ;
			Block block = matrix.blocks[i];
			checkBiHiddenNumber(block) ;
		}

	}

	public void checkBiHiddenNumber(Group group ) {
		Cell[] cells = group.cells;
		for (int i1=0; i1<9; i1++) {
			if (cells[i1].value != null) continue;
			if (cells[i1].getNoCandidates() != 2) continue;
			for (int i2=0; i2<9; i2++) {
				if (i1==i2) continue;
				if (cells[i2].value != null) continue;
				if (cells[i2].getNoCandidates() != 2) continue;
				boolean match=true;
				for (int k=0; k<9; k++) {
					if (group.cells[i1].candidates[k]!=group.cells[i2].candidates[k]){
						match = false;
						break;
					}
				}
				if (match) {
					for (int k=0; k<9; k++) {
						if (group.cells[i1].candidates[k].getValue()) {
							for (int othercell =0; othercell<9; othercell++) {
								if ((othercell!=i1) && (othercell!=i2)) {
									if ((group.cells[othercell].value == null)&& (group.cells[othercell].candidates[k].getValue())) {
										group.cells[othercell].candidates[k].setValue(false);
										if (group.cells[othercell].getNoCandidates()==1) {
											group.cells[othercell].birthForSurplusOne();
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	


}
