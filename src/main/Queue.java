package main;

public class Queue {
	private Node<String> front, back;
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
	
	public void enqueue(String customer) {
		Node<String> newNode = new Node<String>(customer);
		if(isEmpty()) {
			front = newNode;
		}
		else {
			back.setNext(newNode);
		}
		back = newNode;
		size++;
	}
	
	public String dequeue() {
		if(isEmpty()) {
			return null;
		}
		else {
			String customer = front();
			front = front.getNext();
			if(front==back) {
				back = null;
			}
			size--;
			return customer;
		}
	}
	
	public String front() {
		if(isEmpty()) {
			return null;
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
