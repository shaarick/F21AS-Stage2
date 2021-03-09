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
		
	}
	
	
}
