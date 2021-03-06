package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.Discount;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

public class DiscountTest {
	ArrayList<String> typeOne = new ArrayList<String>();
	HashMap<String, ArrayList<String>> typeTwo = new HashMap<String, ArrayList<String>>();
	ArrayList<String> one = new ArrayList<String>();
	ArrayList<String> two = new ArrayList<String>();
	ArrayList<String> three = new ArrayList<String>();

	@BeforeEach
	public void setup() {
		typeOne.add("Rashid, Croissant, 3, 4.5");
		typeOne.add("Rashid, Cranberry Scone, 2, 4");
		typeOne.add("Rashid, Ginger Scone, 1, 3.5");
		typeOne.add("Nicholas, Scottish Cream Scone, 1, 2.5");
		typeOne.add("Nicholas, Jumbo Savory Scone, 1, 3.0");
		typeOne.add("Thomas, Civet Cat, 2, 4.0");
		typeOne.add("Thomas, Raspberry Muffin, 3, 9.0");
		typeOne.add("Thomas, I Need ! Ink Pen, 2, 15.0");

		one.add("Croissant, 3, 4.5");
		one.add("Cranberry Scone, 2, 4");
		one.add("Ginger Scone, 1, 3.5");
		typeTwo.put("Rashid", one);

		two.add("Scottish Cream Scone, 1, 2.5");
		two.add("Jumbo Savory Scone, 1, 3.0");
		typeTwo.put("Nicholas", two);

		three.add("Civet Cat, 2, 4.0");
		three.add("Raspberry Muffin, 3, 9.0");
		three.add("I Need ! Ink Pen, 2, 15.0");
		typeTwo.put("Thomas", three);
	}

	@Test
	@DisplayName("Discount with OrderList of ArrayList type should work")
	public void testDiscountTypeOne() {
		assertEquals("Total £10.5 (with £1.5 discount)", Discount.discountTypeOne("Rashid", typeOne));
		assertEquals("Total £5.5 (no discount)", Discount.discountTypeOne("Nicholas", typeOne));
		assertEquals("Total £25.0 (with £3.0 discount)", Discount.discountTypeOne("Thomas", typeOne));
	}

	@Test
	@DisplayName("Discount with OrderList of HashMap type should work")
	public void testDiscountTypeTwo() {
		assertEquals("Total £10.5 (with £1.5 discount)", Discount.discountTypeTwo("Rashid", typeTwo));
		assertEquals("Total £5.5 (no discount)", Discount.discountTypeTwo("Nicholas", typeTwo));
		assertEquals("Total £25.0 (with £3.0 discount)", Discount.discountTypeTwo("Thomas", typeTwo));
	}
}
