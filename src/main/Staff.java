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
			Customer test = queue.dequeue();
			if (!(test == null)) {
				LogClass.logger.info(Thread.currentThread().getName() + " is serving " + test.getName());
				//System.out.println(Thread.currentThread().getName() + " is serving " + test.getName());
				try { Thread.currentThread().sleep(test.getTotalNumberItems() * 1000); }
				catch (InterruptedException e) {}
				LogClass.logger.info(Thread.currentThread().getName() + " has served " + test.getName());
				//System.out.println(Thread.currentThread().getName() + " has served " + test.getName());
			}
			try { Thread.currentThread().sleep(1000); }
			catch (InterruptedException e) {}
		}
		LogClass.logger.info(Thread.currentThread().getName() + " is done working.");
		//System.out.println(Thread.currentThread().getName() + " is done working.");
		return;
	}
}
