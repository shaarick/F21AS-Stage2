package main;

import java.lang.Math;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * CustomerList class
 * This class regroups all the customers ordering items in a HashSet
 * and feeds the queue with those customers.
 * @author Nicolas JEAN - nj2000 - H00359359
 */

public class CustomerList implements Runnable, Subject2{
    private Set<Customer> customerList;
    private Queue<Customer> queue;
    private List<Observer2> gui;
	
    /**
     * Creates an CustomerList object.
     * @param queueIn the name of the queue that will be fed
     */
    public CustomerList(Queue<Customer> queueIn) {
	customerList = new HashSet<Customer>();
	queue = queueIn;
	gui = new LinkedList<Observer2>();
    };
	
    public Set<Customer> getCustomerList() { return customerList; }
    public Queue<Customer> getQueue() { return queue; }
	
    public void setCustomerList(Set<Customer> customerListIn) {customerList = customerListIn; }
    public void setQueue(Queue<Customer> queueIn) { queue = queueIn; }
	
    /**
     * Adds a customer with his items ordered to the customerList.
     * @param customer the customer to be added to the list
     */
    public void addCustomerToList(Customer customer) {
	for (Customer c : customerList) {
	    if (customer.hashCode() == c.hashCode()) {
	    	LogClass.logger.info("Customer " + c.getName() + " already ordered and is already in the list.");
	    	//System.out.println("Customer " + c.getName() + " already ordered and is already in the list.");
	        return;
	    }
	}
	customerList.add(customer);
	
	LogClass.logger.info(customer.getName() + " added to the customers' list.");
	//System.out.println(customer.getName() + " added to the customers' list.");
	return;
    }
	
    /**
     * Runs the thread that will "produce"
     * by feeding the queue with customers.
     */
    public void run() {
    	registerObserver(new QueueGUI(this.queue));
    	for (Customer c : customerList) {
	    try {Thread.currentThread().sleep((int)((Math.random()* 6 + 1) * Main.getTime()));}
	    catch (InterruptedException e) {}
	    queue.enqueue(c);
	    LogClass.logger.info(c.getName() + " ordered " + c.getTotalNumberItems() + " items. The order has been added to the queue");
	    notifyObservers(c);
	    //System.out.println(c.getName() + " ordered " + c.getTotalNumberItems() + " items. The order has been added to the queue");
	}
	queue.setDone();
	notifyObservers();
	LogClass.logger.info("Orders list is empty");
	//	System.out.println("Orders list is empty");
    }

	@Override
	public void registerObserver(Observer2 observer) {
		// TODO Auto-generated method stub
		gui.add(observer);
	}

	@Override
	public void removeObserver(Observer2 observer) {
		// TODO Auto-generated method stub
		gui.remove(observer);
	}

	@Override
	public void notifyObservers(Customer c) {
		// TODO Auto-generated method stub
		for(Observer2 observer: gui) {
			observer.update(c);
		}
	}
	
	@Override
	public void notifyObservers() {
		for(Observer2 observer: gui) {
			observer.update();
		}
	}
    

}
