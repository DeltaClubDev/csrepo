package unl.cse;

public class Node<T> {
	private final T item;
	private Node<T> next;
	
	public Node(T item) {
		this.item = item;
		next = null;
	}

	public T getItem() {
		return this.item;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	public Node<T> getNext() {
		return this.next;
	}
	
	public boolean hasNext() {
		return (this.next == null) ? true : false;
	}
	
}