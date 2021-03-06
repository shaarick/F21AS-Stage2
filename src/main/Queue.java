package main;

public class Queue<C> {
	private Node<C> front, back;
	private int size;
	
	public Queue() {
		size = 0;
		front = null;
		back = null;
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size==0;
	}
	
	public void enqueue(C customer) {
		Node<C> newNode = new Node<C>(customer);
		if(isEmpty()) {
			front = newNode;
		}
		else {
			back.setNext(newNode);
		}
		back = newNode;
		size++;
	}
	
	public C dequeue() throws EmptyQueueException {
		if(isEmpty()) {
			throw new EmptyQueueException();
		}
		else {
			C customer = front();
			front = front.getNext();
			if(front==back) {
				back = null;
			}
			size--;
			return customer;
		}
	}
	
	public C front() throws EmptyQueueException {
		if(isEmpty()) {
			throw new EmptyQueueException();
		}
		else {
			return front.getValue();
		}
	}
	
	
	
	private class Node<V>{
		private V value;
		private Node<V> next;
		
		public Node(V value) {
			setValue(value);
			setNext(null);
		}
		
		public V getValue() {
			return value;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
		
		public Node<V> getNext() {
			return next;
		}
		
		public void setNext(Node<V> next) {
			this.next = next;
		}
	}
}
