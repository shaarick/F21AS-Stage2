package main;

import javax.swing.*;

public class StaffGUI extends JFrame implements Observer {
	JLabel display;
	Staff staff;
	
	public StaffGUI(String name, Staff staff) {
		setSize(800,400);
		setVisible(true);
		setTitle(name);
		
		display = new JLabel(name,JLabel.CENTER);
		display.setOpaque(true);
		
		add(display);
		
		this.staff = staff;
		this.staff.registerObserver(this);
	}
	
	public void update() {
		display.setText(Thread.currentThread().getName() + " is serving " + staff.getCurrent().getName());
	}
}
