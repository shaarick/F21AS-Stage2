package main;

public class Staff implements Runnable {
//	private Set<Customer> customerList;
	private Queue queue;
	
	public Staff(Queue q) {
		queue = q;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		try {
			Customer test = (Customer) queue.dequeue();
			System.out.println(test.getName());
		} catch (EmptyQueueException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
}
