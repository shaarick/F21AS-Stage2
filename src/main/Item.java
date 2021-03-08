package main;

/**
 * Item class
 * This class defines the items ordered by the customer.
 * @author Nicolas JEAN - nj2000 - H00359359
 */

public class Item {
    private String name;
    private double price;
    private int quantity;
    private double priceTotal;
    
    /**
     * Creates an Item object.
     * @param nameIn the name of the item
     * @param priceIn the individual price of the item
     * @param quantityIn the quantity ordered of that item
     */
    public Item(String nameIn, double priceIn, int quantityIn) {
        name = nameIn;
        price = priceIn;
        quantity = quantityIn;
        priceTotal = price * quantity;
    }
    
    public String getName()       { return name;       }
    public double getPrice()      { return price;      }
    public int    getQuantity()   { return quantity;   }
    public double getPriceTotal() { return priceTotal; }
    
    public void setName(String nameIn) {
    	if (nameIn.trim().length() == 0)
        	throw new IllegalStateException("Item name blank.");
    	name = nameIn;
    }
    
    public void setPrice(double priceIn) {
    	if (priceIn < 0)
        	throw new IllegalStateException("Item price negative.");
    	price  = priceIn;
    }
    
    public void setQuantity(int quantityIn) {
    	if (quantityIn < 1)
        	throw new IllegalStateException("Quantity negative or null.");
    	quantity  = quantityIn;
    }

    public void setPriceTotal() { priceTotal  = getPrice() * getQuantity(); }
    
    /**
     * Necessary method to use an Item in a HashSet
     * by enabling the comparison between two Item objects,
     * their name being the element of comparison
     * @param other the Item object to compare to
     */
    public int compareTo(Item other) {
    	return name.compareTo(other.name);
    }
    
    /**
     * Necessary method to use a Item in a HashSet
     * by enabling the comparison between two Item objects,
     * their name being the element of comparison
     * @param other the Object object to compare to
     */
    public boolean equals(Object other) {
    	if (other instanceof Item) {
    		Item otherItem = (Item) other;
    		if (name.equals(otherItem.name))
    			return true;
    	}
    	return false;
    }
    
    /**
     * Necessary method to use an Item in a HashSet
     * by defining its hashcode; here, it is based
     * on the Item's name
     */
    public int hashCode() { return name.hashCode(); }
}
