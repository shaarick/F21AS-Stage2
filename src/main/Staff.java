package main;
import java.util.*;
/**
 * Staff class
 * This class manages the threads initialized by the different staff members
 * that consume the customers's orders from the queue.
 * @author Shariq FAROOQUI - msf2000 - H00358466
 * + Nicolas JEAN - nj2000 - H00359359 for the run() method
 */

public class Staff implements Runnable, Subject, Subject3 {
	private String name;
	private Queue<Customer> queue;
	private List<Observer> gui;
	private List<Observer3> obs3;
	private Customer current;

	public Staff(String name, Queue<Customer> q) {
		this.name = name;
		queue = q;
		gui = new LinkedList<Observer>();
		obs3 = new LinkedList<Observer3>();
	}

	@SuppressWarnings("static-access")
	public void run() {
		registerObserver(new StaffGUI(name,this));
		registerObserver3(new QueueGUI(queue));
		while (!queue.isTerminated()) {
			current = queue.dequeue();
			if (!(current == null)) {
//				LogClass.logger.info(Thread.currentThread().getName() + " is serving " + current.getName());
				notifyObservers();
				//System.out.println(Thread.currentThread().getName() + " is serving " + test.getName());
				try { Thread.currentThread().sleep(current.getTotalNumberItems() * Main.getTime());}
				catch (InterruptedException e) {}
				LogClass.logger.info(Thread.currentThread().getName() + " has served " + current.getName());
				notifyObservers3(current);
//				changed(current);
//				notifyServed(current);
				//System.out.println(Thread.currentThread().getName() + " has served " + test.getName());
			}
			try { Thread.currentThread().sleep(1000); }
			catch (InterruptedException e) {}
		}
//		LogClass.logger.info(Thread.currentThread().getName() + " is done working.");
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
		// TODO Auto-generated method stub
		for(Observer observer: gui) {
			observer.updateDone();
		}
	}

	@Override
	public void registerObserver3(Observer3 observer) {
		// TODO Auto-generated method stub
		obs3.add(observer);
	}

	@Override
	public void notifyObservers3(Customer c) {
		System.out.println("got into notify");
		// TODO Auto-generated method stub
		for(Observer3 observer: obs3) {
			observer.update3(c);
		}
		
	}
	
//	public synchronized void changed(Customer c) {
//		System.out.println("got into sync method");
//		QueueGUI.customer = c;
//	}

//	@Override
//	public synchronized void notifyServed(Customer c) {
//		// TODO Auto-generated method stub
//		QueueGUI.customer = c;
//	}
	
	
}
