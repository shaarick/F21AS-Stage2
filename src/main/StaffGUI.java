package main;

import java.awt.BorderLayout;

import javax.swing.*;

public class StaffGUI extends JFrame implements Observer {
	JLabel display;
	JTextArea textArea;
	Staff staff;
	String str;
	
	public StaffGUI(String name, Staff staff) {
		setSize(800,400);
		setVisible(true);
		setTitle(name);
		
		display = new JLabel(name,JLabel.CENTER);
		display.setOpaque(true);
		getContentPane().add(display, BorderLayout.PAGE_START);
		
		textArea = new JTextArea();
		getContentPane().add(textArea, BorderLayout.CENTER);
		
		
		this.staff = staff;
		this.staff.registerObserver(this);
	}
	
	public void update() {
		for(Item i: staff.getCurrent().getItemsOrdered()) {
			str += i.getQuantity() + " " + i.getName() + "\n";
		}
		display.setText(Thread.currentThread().getName() + " is serving " + staff.getCurrent().getName());
		textArea.setText(str); 
	}
}
