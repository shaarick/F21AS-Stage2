package main;

public class Staff implements Runnable {
//	private Set<Customer> customerList;
	private Queue<Customer> queue;
	
	public Staff(Queue<Customer> q) {
		queue = q;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		try {
			Customer test = queue.dequeue();
			System.out.println(test.getName());
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
