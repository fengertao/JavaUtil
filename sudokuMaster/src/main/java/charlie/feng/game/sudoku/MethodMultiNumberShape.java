package charlie.feng.game.sudoku;

//The LargeSharp and smallSharp check only 1 candidate number. while here check multi candidate number.
//e.g. In row3, number 2,3,4 can occur in column 7,8,9 only. Other column already exclude the possiblility of those 3 number.
//While in Row6, number 2,3,4 also can occur in column 7,8,9 only.
//So we can know that in row1,2,4,5,7,8,9,    the number 2,3,4 are excluded from column 7,8,9
public class MethodMultiNumberShape implements IMethod {

	public void apply(Matrix matrix) {
		// TODO Auto-generated method stub

	}

}
