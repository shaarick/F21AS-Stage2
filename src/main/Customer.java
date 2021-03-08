package main;

import java.util.HashSet;
import java.util.Set;

/**
 * Customer class
 * This class defines the customers ordering items
 * and regroups the items they ordered in a HashSet.
 * @author Nicolas JEAN - nj2000 - H00359359
 */

public class Customer {
	private String name;
	private static Set<Item> itemsOrdered;
	private double totalOrderAmount;
	private int totalNumberItems;
	
    /**
     * Creates an Customer object.
     * @param nameIn the name of the customer
     */
	public Customer(String nameIn) {
		name = nameIn;
		itemsOrdered = new HashSet<Item>();
		setTotalOrderAmount();
		setTotalNumberItems();
	}
	
	public String getName()		    { return name; 	       }
	public Set<Item> getItemsOrdered()  { return itemsOrdered;     }
	public double getTotalOrderAmount() { return totalOrderAmount; }
	public double getTotalNumberItems() { return totalNumberItems; }
	
	public void setName(String nameIn) {
    	if (nameIn.trim().length() == 0) {
        	throw new IllegalStateException("Customer name blank.");
        }
    	name = nameIn;
    }
	
	public void setItemsOrdered(Set<Item> itemsOrderedIn) {
		itemsOrdered = itemsOrderedIn;
	}

	public void setTotalOrderAmount() {
		double total = 0;
		for (Item io : itemsOrdered) {
			total += io.getPriceTotal();
		}
		//a call to the discounts methods on the total could be done here Shariq
		totalOrderAmount = total;
	}
	
	public void setTotalNumberItems() {
		int total = 0;
		for (Item io : itemsOrdered) {
			total += io.getQuantity();
		}
		totalNumberItems = total;
	}

    /**
     * Adds an Item to the list of items ordered by the customer.
     * @param item the item to add to the list
     */
	public void addToItemsOrdered(Item item) {
		for (Item io : itemsOrdered) {
	        if (item.hashCode() == io.hashCode()) {
	        	System.out.println(io.getName() + " already present in the customer's order.");
	        	return;
	        }
	    }
		itemsOrdered.add(item);
	    System.out.println("-> " + item.getName() + ", " + item.getQuantity() + ", at " + item.getPrice() + " $ each for a total of " + item.getPriceTotal() + "$, added to " + getName() + "'s order.");
	    return;
	}
	
    /**
     * Necessary method to use a Customer in a HashSet
     * by enabling the comparison between two Customer objects,
     * their name being the element of comparison
     * @param other the Customer object to compare to
     */
	public int compareTo(Customer other) {
    	return name.compareTo(other.name);
    }
    
    /**
     * Necessary method to use a Customer in a HashSet
     * by enabling the comparison between two Customer objects,
     * their name being the element of comparison
     * @param other the Object object to compare to
     */
    public boolean equals(Object other) {
    	if (other instanceof Customer) {
    		Customer otherCustomer = (Customer) other;
    		if (name.equals(otherCustomer.name))
    			return true;
    	}
    	return false;
    }
    
    /**
     * Necessary method to use a Customer in a HashSet
     * by defining its hashcode; here, it is based
     * on the Customer's name
     */
    public int hashCode() { return name.hashCode(); }
}
