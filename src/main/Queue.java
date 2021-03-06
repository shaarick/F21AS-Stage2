package main;

public class Queue {
	private Node<Customer> front, back;
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
	
	public void enqueue(Customer customer) {
		Node<Customer> newNode = new Node<Customer>(customer);
		if(isEmpty()) {
			front = newNode;
		}
		else {
			back.setNext(newNode);
		}
		back = newNode;
		size++;
	}
	
	public Customer dequeue() {
		if(isEmpty()) {
			return null;
		}
		else {
			Customer customer = front();
			front = front.getNext();
			if(front==back) {
				back = null;
			}
			size--;
			return customer;
		}
	}
	
	public Customer front() {
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
