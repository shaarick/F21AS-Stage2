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
			try {Thread.currentThread().sleep(100);
				try {
					Customer test = queue.dequeue();
					System.out.println(Thread.currentThread().getName() + " is serving " + test.getName());
					System.out.println(queue.isEmpty());
					if (queue.isTerminated()) {
						notifyAll();
						break;
					}
				} catch (EmptyQueueException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			catch (InterruptedException e) {}
		}
	}
}

//if producer is finished and queue empty ->end program
