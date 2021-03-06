package main;

import java.util.ArrayList;

public class Discount {

	/**
	 * Discount type 1
	 * 
	 * @param Customer  String name of person for whom discount needs to be
	 *                  calculated
	 * @param OrderList ArrayList of String containing all orders
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

	public static void main(String[] args) {
		ArrayList<String> typeOne = new ArrayList<String>();
		typeOne.add("Rashid, Croissant, 3, 4.5");
		typeOne.add("Rashid, Cranberry Scone, 2, 4");
		typeOne.add("Rashid, Ginger Scone, 1, 3.5");
		typeOne.add("Nicholas, Scottish Cream Scone, 1, 2.5");
		typeOne.add("Nicholas, Jumbo Savory Scone, 1, 3.0");

		System.out.println(discountTypeOne("Rashid", typeOne));
		System.out.println(discountTypeOne("Nicholas", typeOne));

	}
}
