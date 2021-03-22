package main;

import java.util.Set;

import javax.swing.*;

public class QueueGUI extends JFrame implements Observer{
	JLabel label;
	Queue<Customer> queue;
	
	public QueueGUI(CustomerList clist) {
		this.queue = clist.getQueue();
		
		this.setSize(500, 400);
        this.setTitle("Queue");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        label = new JLabel("There are currently no people waiting in the queue.", JLabel.CENTER);
        add(label);
        
        setVisible(true);
	}
	@Override
	public void update() {
		label.setText("There are currently " + queue.size() + " people waiting in the queue:");
	}
	
}
