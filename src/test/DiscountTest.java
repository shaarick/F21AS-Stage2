package test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.Customer;
import main.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;


public class DiscountTest {
	Customer one = new Customer("Rashid");
	Customer two = new Customer("Nicholas");
	Customer three = new Customer("Thomas");

	@BeforeEach
	public void setUp() {
		one.addToItemsOrdered(new Item("Croissant", 1.5, 3));
		one.addToItemsOrdered(new Item("Cranberry Scone", 2, 4));
		one.addToItemsOrdered(new Item("Ginger Scone", 3.5, 1));
		one.setTotalOrderAmount();
		
		
		two.addToItemsOrdered(new Item("Scottish Cream Scone", 2.5, 1));
		two.addToItemsOrdered(new Item("Jumbo Savory Scone", 3.0, 1));
		two.setTotalOrderAmount();
		
		three.addToItemsOrdered(new Item("Civet Cat", 2.0, 2));
		three.addToItemsOrdered(new Item("Raspberry Muffin", 3.0, 3));
		three.addToItemsOrdered(new Item("I Need ! Ink Pen", 7.5, 2));
		three.setTotalOrderAmount();
	}
	
	@Test
	@DisplayName("TotalOrderAmount should work")
	public void testAmount() {
		assertEquals(12.5, one.getTotalOrderAmount());
		assertEquals(5.5, two.getTotalOrderAmount());
		assertEquals(25, three.getTotalOrderAmount()); 
	}

	@Test
	@DisplayName("DiscountLine should work")
	public void testDiscount() {
		assertEquals("Total £12.5 (with £3.5 discount)", one.getDiscountLine());
		assertEquals("Total £5.5 (no discount)", two.getDiscountLine());
		assertEquals("Total £25.0 (with £3.0 discount)", three.getDiscountLine());
	}

}
