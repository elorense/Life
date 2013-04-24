public class LinkedList {
	Node n = new Node();
	Node head = new Node();
	Node tail = new Node();
	MyMatrixElem me = new MyMatrixElem();
	Object defaultValue;
	
	public LinkedList(Object defaultValue) {
		this.defaultValue = defaultValue;
	}

	public void insertNode(int location) {
		Node current = head;
		n = new Node();
		n.setData(location);
		// if the linked list is empty, this sets the head and tail.
		if (head.getNext() == null) {
			head.setNext(n);
			n.setNext(tail);
		}
		while (current.getNext().getNext() != null) {
			// if the value of the node being set exists in the list, exits.
			if ((current.getNext().getData().equals(location))) {
				return;
			}
			// find the place for the insertion
			if ((current.getNext().getData().compareTo(location) > 0)) {
				n.setNext(current.getNext());
				current.setNext(n);
				return;
			}
			current = current.getNext();
		}
		// inserts at the end of the list
		n.setNext(current.getNext());
		current.setNext(n);

	}

	public void setRowLink(int location, int index, MyMatrixElem me) {
		Node current = head;

		if (current.getNext() == null) {
			if(!me.value().equals(defaultValue)){
				insertNode(location);
				current.getNext().setNextRowElem(me);
				return;
			}else{
				return;
			}
			
		}

		if (current.getNext().getData().compareTo(location) > 0 ) {
				if(me.value().equals(defaultValue)){
					return;
				}
				insertNode(location);
				current.getNext().setNextRowElem(me);
				return;
			
			
		}
		while (current.getNext().getNext() != null) {

			if ((current.getNext().getData().equals(location))) {
				
					MyMatrixElem temp = current.getNext().getNextRowElem();
					setRowElem(current, index, me, temp);
					return;
				
				

			} else if (current.getNext().getData().compareTo(location) > 0) {
				if(me.value().equals(defaultValue)){
					return;
				}
				insertNode(location);
				current.getNext().setNextRowElem(me);
				// me.setNextRowElem(current.getNextRowElem());
				// current.setNextRowElem(me);
				return;

			}
			current = current.getNext();
		}
		if(!me.value().equals(defaultValue)){
			insertNode(location);
			current.getNext().setNextRowElem(me);
		}
	}
	
	public void deleteRowElem(Node current, MyMatrixElem pointer){

		if(current.getNext().getNextRowElem().getNextRowElem() == null){
			current.setNext(current.getNext().getNext());
		}else if(pointer.equals(current.getNext().getNextRowElem())){
			current.getNext().setNextRowElem(pointer.getNextRowElem());
		}else{
			
			pointer.setNextRowElem(pointer.getNextRowElem().getNextRowElem());
		}
			
		
	}
	
	public void deleteColElem(Node current, MyMatrixElem pointer){
		if(current.getNext().getNextColElem().getNextColElem() == null){
			current.setNext(current.getNext().getNext());
		}else if(pointer.equals(current.getNext().getNextColElem())){
			current.getNext().setNextColElem(pointer.getNextColElem());
		}
		else{
			pointer.setNextColElem(pointer.getNextColElem().getNextColElem());
		}
	}
	public void setRowElem(Node current, int index, MyMatrixElem me,
			MyMatrixElem pointer) {
		MyMatrixElem temp = pointer;
		/*
		 * check to see there is only one element linked to a row, then compares
		 * them
		 */

		if (temp.columnIndex() == index) {
			if(me.value() == defaultValue){
				deleteRowElem(current, temp);
				return;
			}else{
				temp.setValue(me.value());
				return;
			}
			
		}
		if (temp.getNextRowElem() == null) {
			if (temp.columnIndex() > index) {
				
				me.setNextRowElem(current.getNext().getNextRowElem());
				current.getNext().setNextRowElem(me);
			} else if (temp.columnIndex() < index) {
				if(me.value().equals(defaultValue)){

					return;
				}
				temp.setNextRowElem(me);
			}
		} else {
			/*
			 * check to see if the element to be inserted is the first element.
			 */
			
			if (temp.columnIndex() > index) {
				
				me.setNextRowElem(current.getNext().getNextRowElem());
				current.getNext().setNextRowElem(me);

			}else {
				while (temp.getNextRowElem() != null) {
					if(temp.getNextRowElem().columnIndex() == index){
						if(me.value() == defaultValue){
							deleteRowElem(current, temp);
							return;
						}else{
							temp.getNextRowElem().setValue(me.value());
							return;
						}
						
					}else if (temp.getNextRowElem().columnIndex() > index) {
						if(me.value().equals(defaultValue)){

							return;
						}
						me.setNextRowElem(temp.getNextRowElem());
						temp.setNextRowElem(me);
						return;
					}
					temp = temp.getNextRowElem();
				}

					me.setNextRowElem(temp.getNextRowElem());
					temp.setNextRowElem(me);
				
				
			}
		}
	}
	public void setColLink(int location, int index, MyMatrixElem me) {
		Node current = head;
		
		
		if (current.getNext() == null) {
			if(!me.value().equals(defaultValue)){
				insertNode(location);
				current.getNext().setNextColElem(me);
				return;
			}
			return;
		}
		if (current.getNext().getData().compareTo(location) > 0 ) {
			if(me.value().equals(defaultValue)){
				return;
			}
			insertNode(location);
			current.getNext().setNextColElem(me);
			return;
		}
		while (current.getNext().getNext() != null) {

			if ((current.getNext().getData().equals(location))) {
					MyMatrixElem temp = current.getNext().getNextColElem();
					setColElem(current, index, me, temp);
					return;
				
				

			} else if (current.getNext().getData().compareTo(location) > 0) {
				if(me.value().equals(defaultValue)){
					return;
				}
				insertNode(location);
				current.getNext().setNextColElem(me);
				// me.setNextColElem(current.getNextColElem());
				// current.setNextColElem(me);
				return;

			}
			current = current.getNext();
		}
		if(!me.value().equals(defaultValue)){
			insertNode(location);
			current.getNext().setNextColElem(me);
		}
		
	}

	public void setColElem(Node current, int index, MyMatrixElem me,
			MyMatrixElem pointer) {
		MyMatrixElem temp = pointer;
		/*
		 * check to see there is only one element linked to a Col, then compares
		 * them
		 */

		if (temp.rowIndex() == index) {
			if(me.value() == defaultValue){
				deleteColElem(current, temp);
				return;
			}else{
				temp.setValue(me.value());
				return;
			}
		}

		if (temp.getNextColElem() == null) {

			if (temp.rowIndex() > index) {

				me.setNextColElem(current.getNext().getNextColElem());
				current.getNext().setNextColElem(me);
			} else if (temp.rowIndex() < index) {
				if(me.value().equals(defaultValue)){
					return;
				}
				temp.setNextColElem(me);
			}
		} else {
			/*
			 * check to see if the element to be inserted is the first element.
			 */

			if (temp.rowIndex() > index) {

				me.setNextColElem(current.getNext().getNextColElem());
				current.getNext().setNextColElem(me);

			} else {

				while (temp.getNextColElem() != null) {

					if(temp.getNextColElem().rowIndex() == index){
						if(me.value() == defaultValue){
							
							deleteColElem(current, temp);
							return;
						}else{

							temp.getNextColElem().setValue(me.value());
							return;
						}
					}else if (temp.getNextColElem().rowIndex() > index) {
						if(me.value().equals(defaultValue)){
							return;
						}
						me.setNextColElem(temp.getNextColElem());
						temp.setNextColElem(me);
						return;
					}
					temp = temp.getNextColElem();
				}

					me.setNextColElem(temp.getNextColElem());
					temp.setNextColElem(me);
				
				
			}
		}
	}

	public void printAll() {
		Node current = head;
		while (current.getNext().getNext() != null) {
			System.out.println(current.getNext().getData());
			current = current.getNext();
		}
	}

}
