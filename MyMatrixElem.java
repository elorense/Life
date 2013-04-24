public class MyMatrixElem extends Node implements MatrixElem {
	private int rowIndex;
	private int colIndex;
	private Object value;

	public int rowIndex() {
		return rowIndex;
	}

	public int columnIndex() {
		return colIndex;
	}

	public Object value() {
		return value;
	}

	public void setRowIndex(int row) {
		this.rowIndex = row;
	}

	public void setColIndex(int col) {
		this.colIndex = col;
	}

	public void setValue(Object v) {
		this.value = v;
	}

	public String toString() {
		return ("Row: " + rowIndex + " Column: " + colIndex + " Value: " + value);
	}

}
