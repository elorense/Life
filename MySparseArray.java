
public class MySparseArray implements SparseArray{
	LinkedList row;
	LinkedList col;
	private MyMatrixElem me;
	private Object defaultValue;
	
		
	public MySparseArray(Object defaultValue){
		this.defaultValue = defaultValue;
		col = new LinkedList(defaultValue);
		row = new LinkedList(defaultValue);
	}
	
	public Object defaultValue() {
		
		return defaultValue;
	}

	public MyRowIterator iterateRows() {
		return new MyRowIterator();
	}

	public MyColumnIterator iterateColumns() {
		return new MyColumnIterator();
	}

	public Object elementAt(int row, int col) {
		Node current = this.row.head;
		while(current.getNext() != null){
			if(current.getNext().getNext() == null){
				return defaultValue;
			}
			if(current.getNext().getData().equals(row)){
				me = current.getNext().getNextRowElem();
				if(me.columnIndex() == col){
					return me.value();
				}else{
					while(me.getNextRowElem() != null){
						if(me.getNextRowElem().columnIndex() == col){
							return me.getNextRowElem().value();
						}
						me = me.getNextRowElem();
					}
					return defaultValue;
				}
			}
			
			current = current.getNext();
		}
		return defaultValue;
	}

	public void setValue(int row, int col, Object value) {
		me = new MyMatrixElem();
		me.setValue(value);
		me.setRowIndex(row);
		me.setColIndex(col);
		this.col.setColLink(col, me.rowIndex(), me);
		this.row.setRowLink(row, me.columnIndex(), me);
	}

	public void printAllElems() {

		Node current = row.head;
		while (current.getNext() != null) {
			if (current.getNextRowElem() != null) {
				me = current.getNextRowElem();
				while (me != null) {
					System.out.println(me.toString());
					me = me.getNextRowElem();
				}
			}

			current = current.getNext();
		}
	}
	
	public void printAllElemsCols() {
		Node current = col.head;
		while (current.getNext() != null) {
			if (current.getNextColElem() != null) {
				me = current.getNextColElem();
				while (me != null) {
					System.out.println(me.toString());
					me = me.getNextColElem();
				}
			}

			current = current.getNext();
		}
	}
	private class MyRowIterator extends RowIterator{
		Node current;
		MyElemIterator ei;
		
		public MyRowIterator(){
			this.current = row.head.getNext();
		}
		
		public ElemIterator next() {
			ei = new MyElemIterator(true, current);
			current = current.getNext();
			return ei;
		}

		public boolean hasNext() {
			return current != null && current.getNext() != null;

		}
		
	}
	
	private class MyColumnIterator extends ColumnIterator{
		Node current;
		MyElemIterator ei;
		
		public MyColumnIterator(){
			current = col.head;
		}
		
		public ElemIterator next() {
			ei = new MyElemIterator(false, current);
			current = current.getNext();
			return ei;
		}

		public boolean hasNext() {
			if(current.getNext() != null){
				return true;
			}
			return false;
		}
		
	}
	
	private 	class MyElemIterator extends ElemIterator{
		boolean rowCheck;
		int index = 0;
		MyMatrixElem me;
		Node current;
		
		public MyElemIterator(boolean check, Node current){
			rowCheck = check;
			this.current = current;
		}
		
		
		public boolean iteratingRow() {
			if(rowCheck == true){
				return true;
			}
			return false;
		}

		public boolean iteratingCol() {
			if(rowCheck == false){
				return true;
			}
			return false;
		}

		public int nonIteratingIndex() {
			return index;
		}

		public MyMatrixElem next() {
			if(rowCheck == true){
				me = current.getNextRowElem();
				index = me.columnIndex();
				current = current.getNextRowElem();
				
			}else{
				me = current.getNextColElem();
				index = me.rowIndex();
				current = current.getNextColElem();
			}
			return me;
		}

		public boolean hasNext() {
			if(rowCheck == true){
				if(current.getNextRowElem() != null){
					return true;
				}else{
					return false;
				}	
			}else{
				if(current.getNextColElem() != null){
					return true;
				}else{
					return false;
				}
			}
			
		}
		
	}


}
