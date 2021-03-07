package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Input class
 * This class analyzes the OrdersList.txt file containing
 * all the orders made by all the customers.
 * @author Nicolas JEAN - nj2000 - H00359359
 */

public class Input {
	private static CustomerList customerList;
	
	public Input(CustomerList customerListIn) {
		customerList=customerListIn;
	}

	/**
     * Reads a file and go through every of its lines
     * to determine which customer has ordered which items,
     * by creating a customer, adding the corresponding items to the
     * itemsOrdered list of the customer, and then adding the customer
     * to the customerList.
     * @param filename the name of the file to be analyzed
     */
	public void readFile(String filename) {

		try {
			File f = new File(filename);
			Scanner scanner = new Scanner(f);
			
			String customerNameTemp = "";
			Customer customer = new Customer(customerNameTemp);

			while (scanner.hasNextLine()) {
				
				String inputLine = scanner.nextLine();

				if (inputLine.length() != 0) {

					String parts [] = inputLine.split(", ");
			        String customerName = parts[0];

			        if(customerName.equals(customerNameTemp))
			        	processLine(customer, parts);

			        else {
			        	//to avoid adding the customer temp
			        	if (!customerNameTemp.equals("")) {
			        		customer.setTotalOrderAmount();
			        		System.out.println(customer.getName() + "'s total order amount: " + customer.getTotalOrderAmount() + "$.");
			        		customerList.addCustomerToList(customer);
			        	}
			        	customerNameTemp = customerName;
			        	customer = new Customer(customerName);
			        	System.out.println("Customer " + customerName + " created.");
			        	processLine(customer, parts);
			        }
				}
			}
			//to add the last customer of the list
			customer.setTotalOrderAmount();
    		System.out.println(customer.getName() + "'s total order amount: " + customer.getTotalOrderAmount() + "$.");
			customerList.addCustomerToList(customer);
			scanner.close();
		}
		//if the file is not found, stop with system exit
		catch (FileNotFoundException fnf) {
			 System.out.println( filename + " not found ");
			 System.exit(0);
		}
    }

	/**
     * Reads the part of a line containing the info
     * on the item ordered, create the corresponding item, and adds it
     * to the itemsOrdered list of the corresponding customer.
     * @param customer the customer to add the item to
     * @param line the OrdersList.txt line being analyzed
     */
	public void processLine(Customer customer, String line []) {
        String itemName = line[1];
        double itemPrice = Double.parseDouble(line[2]);
        int itemQuantity = Integer.parseInt(line[3]);
        
        Item i = new Item(itemName, itemPrice, itemQuantity);
        customer.addToItemsOrdered(i);
    }
}
