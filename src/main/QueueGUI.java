package main;

import java.awt.BorderLayout;

import javax.swing.*;

public class QueueGUI extends JFrame implements Observer2{
	JLabel label;
	Queue<Customer> queue;
	DefaultListModel<String> model = new DefaultListModel<>();
	private static Integer count = 0;
	public QueueGUI(Queue<Customer> queue) {
		this.queue = queue;
		
		this.setSize(400, 300);
        this.setTitle("Queue");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        label = new JLabel("There are currently no people waiting in the queue.");
        add(label, BorderLayout.PAGE_START);
     
        JList<String> list = new JList<>(model);
        this.add(list);
        
        setVisible(true);
	}
	@Override
	public void update(Customer c) {
		QueueGUI.count++;
		// 0 or 1 Queue size
//		label.setText("There are currently " + queue.size() + " people waiting in the queue:");
		
		// total number queue
		label.setText("There are currently " + count + " people waiting in the queue:");

		if(c.getTotalNumberItems() == 1) {
			model.addElement(c.getName() + "\t" + c.getTotalNumberItems() + " item");
		} else {
			model.addElement(c.getName() + "\t" + c.getTotalNumberItems() + " items");
		}

		
	}
	
}
