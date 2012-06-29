package charlie.feng.game.sudoku;

public class SubGroup {
	public Cell[] cells = new Cell[3];

	public boolean supportNumber(int k) {
		for (int i = 0; i < 3; i++) {
			if (cells[i].candidates[k-1].getValue()) return true;
		}
		return false;
	}
}
