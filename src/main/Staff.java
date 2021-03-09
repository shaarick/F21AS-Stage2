package main;

public class Staff implements Runnable {
//	private Set<Customer> customerList;
	private Queue<Customer> queue;
	
	public Staff(Queue<Customer> q) {
		queue = q;
	}

	@Override
	public void run() {
		while(!queue.isEmpty() && !queue.isTerminated()) {
			try {
				Customer test = queue.dequeue();
				System.out.println(Thread.currentThread().getName() + " is serving " + test.getName());
				System.out.println(queue.isEmpty());
			} catch (EmptyQueueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {Thread.currentThread().sleep(100);}
			catch (InterruptedException e) {}
		}
	}
}

//if producer is finished and queue empty ->end program
