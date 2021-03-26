package main;

import java.awt.GridLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class StaffGUI extends JFrame implements Observer {
	JLabel display;
	JTextArea textArea;
	Staff staff;
	String str;
	JLabel disc;
	protected static Integer count = 0;
	
	public StaffGUI(String name, Staff staff) {
		setSize(300,300);
		setVisible(true);
		setTitle(name);
		
		setLocation();
        
		GridLayout grid = new GridLayout(0,1,0,0);
		this.setLayout(grid);
		
		display = new JLabel(name, SwingConstants.CENTER);
		display.setOpaque(true);
		this.add(display);
		
		
		textArea = new JTextArea();
		JScrollPane scroll = new JScrollPane(textArea);
		this.add(scroll);
		
		disc = new JLabel("",SwingConstants.CENTER);
		this.add(disc);
		
		this.staff = staff;
		this.staff.registerObserver(this);
	}
	
	public synchronized void setLocation() {
		setLocation(300 * (Integer.parseInt(Thread.currentThread().getName().substring(7)) - 1), 375);
		StaffGUI.count++;
	}
	
	public void update() {
		for(Item i: staff.getCurrent().getItemsOrdered()) {
			str += i.getQuantity() + " " + i.getName() + "\n";
		}
		display.setText(Thread.currentThread().getName() + " is serving " + staff.getCurrent().getName());
		textArea.setText(str);
		str = "";
		disc.setText(staff.getCurrent().getDiscountLine());
	}
	
}
