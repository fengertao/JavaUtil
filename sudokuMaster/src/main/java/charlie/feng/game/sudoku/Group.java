package charlie.feng.game.sudoku;

import org.apache.commons.lang.ArrayUtils;

import java.util.HashSet;
import java.util.Set;

public class Group {
    static Set<Integer> expectedSet = new HashSet<Integer>();

    static {
        for (int i = 1; i <= 9; i++) {
            expectedSet.add(i);
        }
    }

    public Cell[] cells;
    int id;
    Matrix matrix;
    SubGroup subGroup[];

    public boolean valueBirthed(int value) {
        for (int i = 0; i < cells.length; i++) {
            if ((cells[i].value != null) && (cells[i].value.intValue() == value)) {
                return true;
            }
        }
        return false;
    }

    public void celebrateCellBirth(Cell cell) {
        int value = cell.value.intValue();
        for (int i = 0; i < 9; i++) {
            Cell neighbor = cells[i];
            if (neighbor.equals(cell)) {

            } else {
                if (neighbor.value == null) {
                    neighbor.candidates[value - 1].setValue(false);
                    if (neighbor.getNoCandidates() == 1) {
                        neighbor.birthForSurplusOne();
                    }
                }
            }
        }
    }

    public void validate() {
        Set<Integer> resultSet = new HashSet<Integer>();
        for (int i = 0; i < 9; i++) {
            if (cells[i].value == null) {
                throw new RuntimeException("value is null");
            }
            resultSet.add(cells[i].value);
        }

        if (resultSet.equals(expectedSet)) {
        } else {
            matrix.result = "Validation Error!!!" + this.getClass().getSimpleName() + " id:" + id;
            //throw new RuntimeException("validate failure, Group id:" + id );

        }
    }

    // remove k from this group candidate number list, it is must in the excludeCells list;
    public void removeNumber(int k, Cell[] excludeCells) {
        for (int i = 0; i < 9; i++) {
            Cell currentCell = cells[i];
            if (ArrayUtils.contains(excludeCells, currentCell)) {
                continue;
            } else {
                if ((currentCell.value == null) && (currentCell.candidates[k - 1].getValue())) {
                    currentCell.candidates[k - 1].setValue(false);
                    if (currentCell.getNoCandidates() == 1) {
                        currentCell.birthForSurplusOne();
                    }
                }
            }
        }
    }

    public Set<Integer> getPossibleOffset(int k, Set<Integer> resultSet) {
        resultSet.clear();
        for (int i = 0; i < 9; i++) {
            if (cells[i].candidates[k - 1].getValue()) {
                resultSet.add(i);
            }
        }
        return resultSet;
    }
}
