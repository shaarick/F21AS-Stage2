package main;

import java.util.HashSet;
import java.util.Set;

/**
 * CustomerList class
 * This class regroups all the customers ordering items in a HashSet
 * and feeds the queue with those customers.
 * @author Nicolas JEAN - nj2000 - H00359359
 */

public class CustomerList implements Runnable{
    private Set<Customer> customerList;
    private Queue queue;
	
    /**
     * Creates an CustomerList object.
     * @param queueIn the name of the queue that will be fed
     */
    public CustomerList(Queue queueIn) {
	customerList = new HashSet<Customer>();
	queue = queueIn;
    };
	
    public Set<Customer> getCustomerList() { return customerList; }
    public Queue getQueue() { return queue; }
	
    public void setCustomerList(Set<Customer> customerListIn) {customerList = customerListIn; }
    public void setQueue(Queue queueIn) { queue = queueIn; }
	
    /**
     * Adds a customer with his items ordered to the customerList.
     * @param customer the customer to be added to the list
     */
    public void addCustomerToList(Customer customer) {
	for (Customer c : customerList) {
	    if (customer.hashCode() == c.hashCode()) {
                System.out.println("Customer " + c.getName() + " already ordered and is already in the list.");
	        return;
	    }
	}
	customerList.add(customer);
	System.out.println(customer.getName() + " added to the customers' list.");
	return;
    }
	
    /**
     * Runs the thread that will "produce"
     * by feeding the queue with customers.
     */
    public void run() {
	for (Customer c : customerList) {
	    try { Thread.sleep(50); }
	    catch (InterruptedException e) {}
		queue.enqueue(c);
	}
	//queue.setDone(); waiting for Andrew to implement it
    }
}
