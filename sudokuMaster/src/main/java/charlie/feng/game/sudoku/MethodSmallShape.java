package charlie.feng.game.sudoku;

import java.util.HashSet;
import java.util.Set;

//Multi HiddenCell(HiddenNumber) display like a "#" in two blocks, so there is exclude reaction to the third block  

//Here processed HiddenCellInSharp, seems HiddenNameInSharp should be same while not confirmed yet.
public class MethodSmallShape implements IMethod {

    public void apply(Matrix matrix) {
        for (int i = 0; i < 3; i++) {
            checkSmallShape(matrix.blocks[i * 3], matrix.blocks[i * 3 + 1], matrix.blocks[i * 3 + 2], true);
            checkSmallShape(matrix.blocks[i], matrix.blocks[i + 3], matrix.blocks[i + 6], false);
        }
    }

    public void checkSmallShape(Block block0, Block block1, Block block2, boolean isRow) {
        for (int k = 1; k <= 9; k++) {
            if ((block0.valueBirthed(k)) || (block1.valueBirthed(k)) || (block2.valueBirthed(k))) {
                continue;
            }
            checkHiddenCellInSharp(k, block0, block1, block2, isRow);
            checkHiddenCellInSharp(k, block0, block2, block1, isRow);
            checkHiddenCellInSharp(k, block1, block2, block0, isRow);
            checkHiddenNumberInSharp(k, block0, block1, block2, isRow);
            checkHiddenNumberInSharp(k, block0, block2, block1, isRow);
            checkHiddenNumberInSharp(k, block1, block2, block0, isRow);
        }
    }

    public void checkHiddenCellInSharp(int number, Block source1, Block source2, Block target1, boolean isRow) {
        Set<Integer> sourceSet1 = new HashSet<Integer>();
        Set<Integer> sourceSet2 = new HashSet<Integer>();
        sourceSet1 = source1.getPossibleSubGroup(number, isRow, sourceSet1);
        sourceSet2 = source2.getPossibleSubGroup(number, isRow, sourceSet2);

        if ((sourceSet1.size() == 2) && (sourceSet2.size() == 2)) {
            if (sourceSet1.equals(sourceSet2)) {
                Integer[] subgroupArray = sourceSet1.toArray(new Integer[2]);
                int excludeSubGroup = 0 + 1 + 2 - subgroupArray[0] - subgroupArray[1];
                target1.removeNumber(number, (isRow ? target1.subRows[excludeSubGroup] : target1.subColumns[excludeSubGroup]).cells);
            }
        }
    }

    public void checkHiddenNumberInSharp(int number, Block source1, Block source2, Block target1, boolean isRow) {
        //Here processed HiddenCellInSharp, seems HiddenNameInSharp should be same while not confirmed yet.
    }


}
