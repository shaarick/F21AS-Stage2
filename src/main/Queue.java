package main;

/**
 * Queue class
 * 
 * @author Andrew MASON - wam4 - H00267387
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

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public synchronized boolean isTerminated() {
		if (getDone() && !isEmpty())
			return false;
		else if (!getDone() && !isEmpty())
			return false;
		else if (!getDone() && isEmpty())
			return false;
		else
			return true;
	}

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

	public C front() {
		if (isEmpty()) {
			return null;
		} else {
			return front.getValue();
		}
	}

	public boolean getDone() {
		return done;
	}

	public void setDone() {
		done = true;
	}

	private class Node<V> {
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
