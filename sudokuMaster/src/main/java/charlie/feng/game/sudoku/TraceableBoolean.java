package charlie.feng.game.sudoku;

public class TraceableBoolean {
    public boolean traceFlag;
    private boolean value;
    private Cell parent;
    private int number;

    public TraceableBoolean(Cell cell, int number) {
        this.value = true;
        this.parent = cell;
        this.number = number;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        if (traceFlag) {
            if (this.value != value) {
                System.err.println("Cell row:" + parent.rowId + " col:" + parent.columnId + " number:" + number + " changed");
                try {
                    throw new Exception("Trace Exception");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        this.value = value;
    }

}
