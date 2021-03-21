package main;

import javax.swing.*;

public class StaffGUI extends JFrame {
	JLabel display;
	
	public StaffGUI(String name) {
		setSize(800,400);
		setVisible(true);
		setTitle(name);
		
		display = new JLabel("Hello",JLabel.CENTER);
		display.setOpaque(true);
		
		add(display);
	}
}
