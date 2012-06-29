package charlie.feng.game.sudoku;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SodukuMaster {

	List<IMethod> methods = new ArrayList<IMethod>();

	public void play(Matrix matrix) {
		Date startTime = new Date();
		initialMethod();
		int cycle = 0;
		while ((!matrix.isResolved()) && (cycle < 20)) {
			cycle++;
//			System.out.println("Cycle " + toLength(cycle, 4)
//					+ " start. confirmed cell:"
//					+ toLength(matrix.confirmedCells(), 2)
//					+ " totalCandidates:"
//					+ toLength(matrix.totalCandidates(), 4));
			for (Iterator<IMethod> iterator = methods.iterator(); iterator
					.hasNext();) {
				IMethod method = (IMethod) iterator.next();
				String methodName = method.getClass().getSimpleName();
				matrix.processMethodName = methodName;
				method.apply(matrix);
			}
		}

		if (matrix.isResolved()) {
			matrix.result = "Resolved.";
			matrix.validate();
			Date endTime = new Date();

			System.out.println("Successfully Resolved!!!");
			System.out.println("Start Time:" + startTime);
			System.out.println("End Time:  " + endTime);
			System.out.println("time spend:"
					+ (endTime.getTime() - startTime.getTime()));
		}
	}

	public void initialMethod() {
		methods.add(new MethodDirectExclusive());
		methods.add(new MethodTheOnlyCellSupportNumber());
		methods.add(new MethodBiHiddenCellsExclusive());
		methods.add(new MethodTriHiddenCellsExclusive());
		methods.add(new MethodLastBaby());
		methods.add(new MethodSurplusOne());
		methods.add(new MethodBiNumberLeft());
		methods.add(new MethodTriNumberLeft());
		methods.add(new MethodBiHiddenNumberExclusive());
		methods.add(new MethodTriHiddenNumberExclusive());
		methods.add(new MethodBiSurplusTwoExclusive());
		methods.add(new MethodTriSurplusThreeExclusive());
		methods.add(new MethodFourSurplusFourExclusive());
		methods.add(new MethodSmallShape());
		methods.add(new MethodLargeShape());
		methods.add(new MethodMultiNumberShape());// todo
		methods.add(new MethodLShapeDoubleHiddenNumber()); // Unclear detail;
	}

	public String toLength(int i, int len) {
		return toLength(String.valueOf(i), len);
	}

	public String toLength(String s, int len) {
		if (s == null) {
			s = "";
		}
		int moreLength = len - s.length();
		for (int i = 0; i < moreLength; i++) {
			s = " " + s;
		}
		return s;

	}

}
