package main;

import java.util.HashSet;
import java.util.Set;

/**
 * Customer class
 * This class defines the customers ordering items
 * and regroups the items they ordered in a HashSet.
 * @author Nicolas JEAN - nj2000 - H00359359
 * + Shariq FAROOQUI - msf2000 - H00358466 for the Discount() and getDiscountLine() methods
 */

public class Customer {
    private String name;
    private Set<Item> itemsOrdered;
    private double totalOrderAmount;
    private int totalNumberItems;
    private double discount;

    /**
     * Creates an Customer object.
     * @param nameIn the name of the customer
     */
    public Customer(String nameIn) {
    	name = nameIn;
    	discount = 0.0;
	itemsOrdered = new HashSet<Item>();
	setTotalOrderAmount();
	setTotalNumberItems();
    }
	
    public String getName()		{ return name; 	           }
    public Set<Item> getItemsOrdered()  { return itemsOrdered;     }
    public double getTotalOrderAmount() { return totalOrderAmount; }
    public int getTotalNumberItems()    { return totalNumberItems; }
    public double getDiscount()         { return discount; }
	
    public void setName(String nameIn) {
    	if (nameIn.trim().length() == 0)
       	    throw new IllegalStateException("Customer name blank.");
    	name = nameIn;
    }
	
    public void setItemsOrdered(Set<Item> itemsOrderedIn) {
	itemsOrdered = itemsOrderedIn;
    }

    public void setTotalOrderAmount() {
	double total = 0;
	for (Item io : itemsOrdered)
		total += io.getPriceTotal();
	totalOrderAmount = total;
	//Discount();
    }
	
    public void setTotalNumberItems() {
	int total = 0;
	for (Item io : itemsOrdered)
	    total += io.getQuantity();
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
	System.out.println("-> " + item.getName() + ", " + item.getQuantity() + ", at " + item.getPrice() + " £ each for a total of " + item.getPriceTotal() + "£, added to " + getName() + "'s order.");
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
    
    /**
     * Applies the discount rule to our total order amount
     * and updates the value.
     */
    public void Discount() {
    	// Loop over all items this customer ordered
    	for(Item item: itemsOrdered) {
    		// Buy 3 or more of same item and get one free
    		if (item.getQuantity() >= 3) {
    			totalOrderAmount -= item.getPrice();
    			discount += item.getPrice();
    		}
    	}
    	
    }
    
    /**
     * Method to get discount details which can directly be 
     * used in the GUI.
     * @return String containing Total and Discount amount
     */
    public String getDiscountLine() {
   
		if (discount > 0) {
			return "Total Â£" + totalOrderAmount + " (with Â£" + discount + " discount)";
		} else {
			return "Total Â£" + totalOrderAmount + " (no discount)";
		}
    }
}
