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

public class CustomerList implements Runnable {
    private Set<Customer> customerList;
    private Queue<Customer> queue;
	
    /**
     * Creates an CustomerList object.
     * @param queueIn the name of the queue that will be fed
     */
    public CustomerList(Queue<Customer> queueIn) {
	customerList = new HashSet<Customer>();
	queue = queueIn;
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
    	for (Customer c : customerList) {
	    try {Thread.currentThread().sleep((int)((Math.random()* 6 + 1) * Main.getTime()));}
	    catch (InterruptedException e) {}
	    queue.enqueue(c);
	    LogClass.logger.info(c.getName() + " ordered " + c.getTotalNumberItems() + " items. The order has been added to the queue");
	    QueueGUI.update(c);
	    
	    //System.out.println(c.getName() + " ordered " + c.getTotalNumberItems() + " items. The order has been added to the queue");
	}
	queue.setDone();
	LogClass.logger.info("Orders list is empty");
	//	System.out.println("Orders list is empty");
    }

}
