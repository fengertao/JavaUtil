package charlie.feng.game.sudoku;

public class Cell {
	Integer value;
	public TraceableBoolean[] candidates;
	int rowId;
	int columnId;
	int blockId;
	Matrix matrix;
	
	boolean noticedNeighbor=false;
	
	public Cell(Matrix matrix, int r, int c, int b) {
		candidates = new TraceableBoolean[9];
		for (int k = 0; k < 9; k++) {
			candidates[k] = new TraceableBoolean(this, k+1);
		}
		this.matrix = matrix;
		this.rowId = r;
		this.columnId = c;
		this.blockId = b;
	}
	
	public void setValue(Integer value) {
		this.value=value;
		for (int i = 0; i < 9; i++) {
			if (value!=null) {
				if (value.intValue() != i+1) {
					candidates[i].setValue(false);
				}
			}
		}
	}
	
	public Integer getValue(){
		return this.value;
	}
	
	public int getNoCandidates() {
		int totalCandidates = 0;
		for (int k = 0; k < 9; k++) {
			if (candidates[k].getValue() ) {totalCandidates++;}
		}
		return totalCandidates;
	}
	
	public void birthForSurplusOne() {
		int birthValue = 0;
		int candidatesInCell = 0;
		for (int k = 0; k < 9; k++) {
			if (candidates[k].getValue() ) {birthValue=k+1; candidatesInCell++;}
		}
		
		if (candidatesInCell!=1) {
			matrix.dump();
			throw new RuntimeException("Wrong birthForOnlyCandidate, row:" + rowId + " column:"+ columnId);
		} else {
			birth(birthValue, "MethodSurplusOne");
		}
	}
	
	public void birth(int iValue, String LowLevelMethod) {
			value = new Integer(iValue);
			if (rowId==5 && columnId==7) {
			}
			for (int k2 = 0; k2 < 9; k2++) {
				if (k2 != iValue-1 ) {candidates[k2].setValue(false);}
			}
			
			System.out.println("I'm alive, row:" + rowId + " col:" +columnId + " value:" + value +" " + matrix.processMethodName);
			noticeNeighboorsAboutBirth();
	}

	public void noticeNeighboorsAboutBirth() {
		if (value == null) {
			matrix.dump();
			throw new RuntimeException("Wrong noticeNeighboorsAboutBirth, row:" + rowId + " column:"+ columnId);
		}
		if (!noticedNeighbor) {
			noticedNeighbor = true;
			matrix.rows[rowId].celebrateCellBirth(this);
			matrix.columns[columnId].celebrateCellBirth(this);
			matrix.blocks[blockId].celebrateCellBirth(this);
		}
	}
	
}
