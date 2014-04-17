package unl.cse;


public class DerpsList<T> extends Comparing{
	private Node<T> head, tail = null;
	private int size = 0;
	public long sex;
	public double penetration;
	
	public void clear() {
		if (this.head == null) {
			return;
		}
		this.head.setNext(null);
		this.head = this.tail;
	}
	
	public void addStart(T item) {
		Node<T> toHead = new Node<T>(item);
		toHead.setNext(this.head);
		this.head = toHead;
		if (this.tail == null) {
			this.head = this.tail;
		}
		this.size++;
	}
	
	public void addEnd(T item) {
		Node<T> toEnd = new Node<T>(item);
		toEnd.setNext(this.tail);
		this.size++;
	}
	
	public void removeAtIndex(int index) {
		if (index < 0 || index > this.getSize()) {
    		return;
    	}
		
		Node<T> cache = this.head;
		for (int i=0; i<index; i++) {
    		if (cache.getNext() == null) {
    			return;
    		}
    		cache = cache.getNext();
    	}
    	cache.setNext(cache.getNext().getNext());
	}
	
	private Node<T> getNodeAtIndex(int index) {	
		if(index < 0 || index >= this.getSize()) {
			throw new IllegalArgumentException("index out of bounds");
		}
		Node<T> current = head;
		for(int i=0; i<index; i++) {
			current = current.getNext();
		}
		return current;
	}
	
	public T getElementAtIndex(int index) {
    	if (index < 0 || index >= this.getSize()) {
    		throw new IllegalArgumentException("Index out of bounds");
    	}
		Node<T> cache = this.getNodeAtIndex(index);
		return cache.getItem();
	}
	
	public int getSize() {
		return this.size;
	}
}