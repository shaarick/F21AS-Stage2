package main;
import java.util.*;
/**
 * Staff class
 * This class manages the threads initialized by the different staff members
 * that consume the customers's orders from the queue.
 * @author Shariq FAROOQUI - msf2000 - H00358466
 * + Nicolas JEAN - nj2000 - H00359359 for the run() method
 */

public class Staff implements Runnable, Subject {
	private String name;
	private Queue<Customer> queue;
	private List<Observer> gui;
	private Customer current;

	public Staff(String name, Queue<Customer> q) {
		this.name = name;
		queue = q;
		gui = new LinkedList<Observer>();
	}

	@SuppressWarnings("static-access")
	public void run() {
		registerObserver(new StaffGUI(name,this));
		while (!queue.isTerminated()) {
			current = queue.dequeue();
			if (!(current == null)) {
				LogClass.logger.info(Thread.currentThread().getName() + " is serving " + current.getName());
				notifyObservers();
				//System.out.println(Thread.currentThread().getName() + " is serving " + test.getName());
				try { Thread.currentThread().sleep(current.getTotalNumberItems() * CustomerList.getTime()); }
				catch (InterruptedException e) {}
				LogClass.logger.info(Thread.currentThread().getName() + " has served " + current.getName());
				//System.out.println(Thread.currentThread().getName() + " has served " + test.getName());
				notifyServed(current);
//				QueueGUI.decQ();
				QueueGUI.transfer(current);
			}
			try { Thread.currentThread().sleep(CustomerList.getRTime()); }
			catch (InterruptedException e) {}
		}
		LogClass.logger.info(Thread.currentThread().getName() + " is done working.");
		notifyDone();
		
		//System.out.println(Thread.currentThread().getName() + " is done working.");
		return;
	}
	
	public Customer getCurrent() {
		return current;
	}

	public void registerObserver(Observer observer) {
		gui.add(observer);
	}

	public void removeObserver(Observer observer) {
		gui.remove(observer);
		
	}

	public void notifyObservers() {
		for(Observer observer: gui) {
			observer.update();
		}
	}

	@Override
	public void notifyDone() {
		for(Observer observer: gui) {
			observer.updateDone();
		}
	}

	@Override
	public void notifyServed(Customer c) {
		for(Observer observer: gui) {
			observer.updateServed(c);
		}
	}
	
}
