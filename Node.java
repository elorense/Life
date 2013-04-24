public class Node {
	private Comparable o = null;
	private Node next = null;
	private MyMatrixElem nextRowElem = null;
	private MyMatrixElem nextColElem = null;

	public Comparable getData() {
		return o;
	}

	public void setData(Comparable o) {
		this.o = o;
	}

	public void setNextRowElem(MyMatrixElem nextElem) {
		this.nextRowElem = nextElem;
	}

	public MyMatrixElem getNextRowElem() {
		return nextRowElem;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public MyMatrixElem getNextColElem() {
		return nextColElem;
	}

	public void setNextColElem(MyMatrixElem nextColElem) {
		this.nextColElem = nextColElem;
	}

}
