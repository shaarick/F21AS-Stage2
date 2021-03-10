package main;

/**
 * Staff class
 * This class manages the threads initialized by the different staff members
 * that consume the customers's orders from the queue.
 * @author Shariq FAROOQUI - msf2000 - H00358466
 * + Nicolas JEAN - nj2000 - H00359359 for the run() method
 */

public class Staff implements Runnable {
	private Queue<Customer> queue;

	public Staff(Queue<Customer> q) {
		queue = q;
	}

	public void run() {
		while (!queue.isTerminated()) {
			//try {
				Customer test = queue.dequeue();
				if (!(test == null))
					System.out.println(Thread.currentThread().getName() + " is serving " + test.getName());
			/*} catch (EmptyQueueException e) {
				e.printStackTrace();
			}*/
			try { Thread.currentThread().sleep(1000); }
			catch (InterruptedException e) {}
		}
		System.out.println(Thread.currentThread().getName() + " is done working.");
		return;
	}
}
