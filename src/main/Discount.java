package main;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Static discount methods that can be accessed anywhere in the main package.
 * Writing methods with different parameters to and will use whichever is
 * convinient.
 * 
 * The String name parameter for all these methods can be changed to a customer
 * object and then we just use customer.getName and string equals to continue.
 * 
 * @author Shariq Farooqui
 *
 */
public class Discount {

	public static void main(String[] args) {
		ArrayList<String> typeOne = new ArrayList<String>();
		typeOne.add("Rashid, Croissant, 3, 4.5");
		typeOne.add("Rashid, Cranberry Scone, 2, 4");
		typeOne.add("Rashid, Ginger Scone, 1, 3.5");
		typeOne.add("Nicholas, Scottish Cream Scone, 1, 2.5");
		typeOne.add("Nicholas, Jumbo Savory Scone, 1, 3.0");
		typeOne.add("Thomas, Civet Cat, 2, 4.0");
		typeOne.add("Thomas, Raspberry Muffin, 3, 9.0");
		typeOne.add("Thomas, I Need ! Ink Pen, 2, 15.0");

		System.out.println("Type one Discounts:");
		System.out.println(discountTypeOne("Rashid", typeOne));
		System.out.println(discountTypeOne("Nicholas", typeOne));
		System.out.println(discountTypeOne("Thomas", typeOne));

		HashMap<String, ArrayList<String>> typeTwo = new HashMap<String, ArrayList<String>>();

		// These arraylists would be created in a for loop when reading the menu file,
		// but for now
		// I had to manually create two seperate ones.
		ArrayList<String> one = new ArrayList<String>();
		one.add("Croissant, 3, 4.5");
		one.add("Cranberry Scone, 2, 4");
		one.add("Ginger Scone, 1, 3.5");
		typeTwo.put("Rashid", one);

		ArrayList<String> two = new ArrayList<String>();
		two.add("Scottish Cream Scone, 1, 2.5");
		two.add("Jumbo Savory Scone, 1, 3.0");
		typeTwo.put("Nicholas", two);
		
		ArrayList<String> three = new ArrayList<String>();
		three.add("Civet Cat, 2, 4.0");
		three.add("Raspberry Muffin, 3, 9.0");
		three.add("I Need ! Ink Pen, 2, 15.0");
		typeTwo.put("Thomas", three);

		System.out.println("Type two Discounts:");
		System.out.println(discountTypeTwo("Rashid", typeTwo));
		System.out.println(discountTypeTwo("Nicholas", typeTwo));
		System.out.println(discountTypeTwo("Thomas", typeTwo));

	}

	/**
	 * Discount type 1
	 * 
	 * @param Customer  String name of person for whom discount needs to be
	 *                  calculated
	 * @param OrderList ArrayList of String containing all orders Orderlist format:
	 *                  Name, item, quantity, total price
	 */
	public static String discountTypeOne(String name, ArrayList<String> orders) {
		Double total = 0.0;
		Double discount = 0.0;

		// Loop over ArrayList of orders
		for (int i = 0; i < orders.size(); i++) {
			// Split each line by comma
			String[] line = orders.get(i).split(",");
			// If name matches,
			if (line[0].equals(name)) {
				// we look for item quantities
				int quantity = Integer.parseInt(line[2].trim());
				if (quantity < 3) {
					// Total unchanged if < 3
					total += Double.parseDouble(line[3].trim());
				} else if (quantity == 3) {
					// One free item if 3
					total += (2.0 / 3.0) * Double.parseDouble(line[3].trim());
					discount += (1.0 / 3.0) * Double.parseDouble(line[3].trim());
				} else {
					// Assuming no new discount rule and that you still only get one free item
					Double price = Double.parseDouble(line[3].trim()) / Double.parseDouble(line[2].trim());
					total += (Double.parseDouble(line[3].trim()) - price);
					discount += price;
				}
			}
		}

		if (discount > 0) {
			String discountLine = "Total £" + total + " (with £" + discount + " discount)";
			return discountLine;
		} else {
			String discountLine = "Total £" + total + " (no discount)";
			return discountLine;
		}
	}

	/**
	 * This method assumes that the orderlist is a HashMap with Customers name as
	 * key and an arraylist of their orders as the values.
	 * 
	 * @param name   Customer name
	 * @param orders HashMap containing all orders Orderlist format: item, quantity,
	 *               total price
	 */
	public static String discountTypeTwo(String name, HashMap<String, ArrayList<String>> orders) {
		Double total = 0.0;
		Double discount = 0.0;

		for (int i = 0; i < orders.get(name).size(); i++) {
			String[] line = orders.get(name).get(i).split(",");
			int quantity = Integer.parseInt(line[1].trim());

			if (quantity < 3) {
				total += Double.parseDouble(line[2].trim());
			} else if (quantity == 3) {
				total += (2.0 / 3.0) * Double.parseDouble(line[2].trim());
				discount += (1.0 / 3.0) * Double.parseDouble(line[2].trim());
			} else {
				Double price = Double.parseDouble(line[2].trim()) / Double.parseDouble(line[1].trim());
				total += (Double.parseDouble(line[2].trim()) - price);
				discount += price;
			}

		}

		if (discount > 0) {
			String discountLine = "Total £" + total + " (with £" + discount + " discount)";
			return discountLine;
		} else {
			String discountLine = "Total £" + total + " (no discount)";
			return discountLine;
		}
	}
}
