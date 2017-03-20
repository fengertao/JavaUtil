package charlie.feng.game.sudoku;

import java.util.HashSet;
import java.util.Set;

public class MethodLargeShape implements IMethod {

    public void apply(Matrix matrix) {
        for (int k = 1; k <= 9; k++) {
            checkLargeShape(matrix, k, true);
            checkLargeShape(matrix, k, false);
        }
    }

    public void checkLargeShape(Matrix matrix, int value, boolean isRow) {
        Set<Integer> possibleOffsetSet1 = new HashSet<Integer>();
        Set<Integer> possibleOffsetSet2 = new HashSet<Integer>();

        for (int i1 = 0; i1 < 9; i1++) {
            Group group1 = (isRow ? matrix.rows[i1] : matrix.columns[i1]);
            if (group1.valueBirthed(value)) continue;
            group1.getPossibleOffset(value, possibleOffsetSet1);
            for (int i2 = 0; i2 < 9; i2++) {
                if (i2 == i1) continue;
                Group group2 = (isRow ? matrix.rows[i2] : matrix.columns[i2]);
                if (group2.valueBirthed(value)) continue;
                group2.getPossibleOffset(value, possibleOffsetSet2);
                if ((possibleOffsetSet1.size() == 2) && (possibleOffsetSet2.size() == 2)) {
                    if (possibleOffsetSet1.equals(possibleOffsetSet2)) {
                        //large shape detected, start clear
                        Integer[] offsetArray = possibleOffsetSet1.toArray(new Integer[2]);
                        for (int i3 = 0; i3 < 9; i3++) {
                            if ((i3 == i1) || (i3 == i2)) {
                                continue;
                            }
                            Group group3 = (isRow ? matrix.rows[i3] : matrix.columns[i3]);
                            group3.cells[offsetArray[0]].candidates[value - 1].setValue(false);
                            group3.cells[offsetArray[1]].candidates[value - 1].setValue(false);

                        }
                    }
                }
            }

        }

    }

}
