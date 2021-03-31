package main;

/**
 * Queue class
 * 
 * @author Andrew MANSON - wam4 - H00267387
 * + Nicolas JEAN - nj2000 - H00359359 for the isTerminated() method
 */

public class Queue<C> {
	private Node<C> front, back;
	private int size;
	private boolean done;

	public Queue() {
		size = 0;
		front = null;
		back = null;
		done = false;
	}

	/**
	 * 
	 * @return size of the queue
	 */
	public int size() {
		return size;
	}

	/**
	 * 
	 * @return if queue is empty or not
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 
	 * @return if queue is terminated
	 */
	public synchronized boolean isTerminated() {
		if (getDone() && isEmpty())
			return true;
		else
			return false;
	}

	
	/**
	 * Add customer to back of queue
	 * @param customer
	 */
	public synchronized void enqueue(C customer) {
		Node<C> newNode = new Node<C>(customer);
		if (isEmpty()) {
			front = newNode;
		} else {
			back.setNext(newNode);
		}
		back = newNode;
		size++;
		notifyAll();
	}

	/**
	 * Remove customer from front of queue
	 * @return customer at front of queue
	 */
	public synchronized C dequeue() {
		if (isEmpty()) {
			try { wait(); }
			catch (InterruptedException e) {}
		}
		C customer = front();
		if (!isEmpty()) {
			if (front == back)
				back = null;
			front = front.getNext();
			size--;
		}
		return customer;
	}

	/**
	 * 
	 * @return customer at front of queue
	 */
	public C front() {
		if (isEmpty()) {
			return null;
		} else {
			return front.getValue();
		}
	}

	/**
	 * 
	 * @return if queue is done
	 */
	public boolean getDone() {
		return done;
	}

	/**
	 * Set done variable to true
	 */
	public void setDone() {
		done = true;
	}

	
	/**
	 * Node class for linked list
	 * @author Andrew Manson
	 *
	 * @param <V>
	 */
	private class Node<V> {
		private V value;
		private Node<V> next;

		public Node(V value) {
			setValue(value);
			setNext(null);
		}

		/**
		 * 
		 * @return value of node
		 */
		public V getValue() {
			return value;
		}
		
		/**
		 * Set value of node
		 * @param value
		 */
		public void setValue(V value) {
			this.value = value;
		}
		
		/**
		 * 
		 * @return next node
		 */
		public Node<V> getNext() {
			return next;
		}

		/**
		 * Set next node
		 * @param next
		 */
		public void setNext(Node<V> next) {
			this.next = next;
		}
	}
}
