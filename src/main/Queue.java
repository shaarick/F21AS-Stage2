package main;

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
		return (getDone() && isEmpty());
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
	}

	public synchronized C dequeue() throws EmptyQueueException {
		while (isEmpty()) { // when slot not available
			try { wait(); } // producer enters Waiting state
			catch (InterruptedException e) {
			}
		}
		/*
		 * if(isEmpty()) { throw new EmptyQueueException(); }
		 */
		notifyAll();
		if (front == back) {
			back = null;
		}
		C customer = front();
		front = front.getNext();
		size--;
		return customer;
	}

	public C front() throws EmptyQueueException {
		if (isEmpty()) {
			throw new EmptyQueueException();
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
