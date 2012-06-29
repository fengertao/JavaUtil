package charlie.feng.game.sudoku;

import javax.servlet.http.HttpServletRequest;

public class Matrix {
	public Cell[][] cells;
	public Row[] rows;
	public Column[] columns;
	public Block[] blocks;
	public String result = "";
	public String processMethodName = ""; // should not define here, but we want cell report what's current method name when brith. Do we need AOP for this variable.
	
	public int confirmedCells() {
		int confirmedCells = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (cells[i][j].getValue() != null) {confirmedCells++;}
			}
		}
		return confirmedCells;
	}

	public int totalCandidates() {
		int totalCandidates = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				totalCandidates +=cells[i][j].getNoCandidates();
			}
		}
		return totalCandidates;
	}

	public boolean isResolved() {
		return (confirmedCells() == 81);
	}
	
	public void initial()	{
		cells = new Cell[9][9];
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				cells[r][c]= new Cell(this, r, c, r/3*3+c/3);
			}
		}

		rows = new Row[9];
		for (int r = 0; r < 9; r++) {
			rows[r] = new Row(this, r); 
		}
		
		columns = new Column[9];
		for (int c = 0; c < 9; c++) {
			columns[c] = new Column(this, c); 
		}
		
		blocks = new Block[9];
		for (int i = 0; i < 9; i++) {
			blocks[i] = new Block(this, i); 
		}
	}
	
	public void acceptInput(HttpServletRequest req) {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				String cellId="cell"+r+c;
				String sValue = req.getParameter(cellId);
				Integer iValue = null;
				if (sValue.equals("")) {
					iValue= null;
				} else {
					iValue = new Integer(sValue);
					cells[r][c].setValue(iValue);
				}
			}
		}
	}
		
	public void validate() {
		for (int i = 0; i < 9; i++) {
			Row row = rows[i];
			row.validate() ;
			Column column = columns[i];
			column.validate() ;
			Block block = blocks[i];
			block.validate() ;
		}
	}
	
	public void dump() {
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				Integer value = cells[r][c].value;
				System.out.print((value == null?" ":value.toString()));
			}
			System.out.println("");			
		}
	}
	
}
