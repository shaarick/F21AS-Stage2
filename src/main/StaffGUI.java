package main;

import javax.swing.*;

public class StaffGUI extends JFrame implements Observer {
	JLabel display;
	
	public StaffGUI(String name, Subject subject) {
		setSize(800,400);
		setVisible(true);
		setTitle(name);
		
		display = new JLabel(name,JLabel.CENTER);
		display.setOpaque(true);
		
		add(display);
		
		subject.registerObserver(this);
	}
	
	public void update() {
		
	}
}
