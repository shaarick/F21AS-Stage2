package main;

import java.awt.BorderLayout;

import javax.swing.*;

@SuppressWarnings("serial")
public class QueueGUI extends JFrame implements Observer2{
	JLabel label;
	Queue<Customer> queue;
	DefaultListModel<String> model = new DefaultListModel<>();
	private static Integer count = 0;
	public QueueGUI(Queue<Customer> queue) {
		this.queue = queue;
		
		this.setSize(600, 350);
        this.setTitle("Queue");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setLocationRelativeTo(null);
		
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
		if (!queue.isTerminated()) {
			label.setText("There are currently " + queue.size() + " people waiting in the queue:");	
		} else {
			label.setText("Finished serving all customers in the queue.");
		}
		
		if((queue.size() >1) & (StaffGUI.count < 4)) {
			StaffGUI.count++;
			String name = "Server " + StaffGUI.count;
			Staff three = new Staff(name, queue);
			Thread serverThree = new Thread(three, name);
			serverThree.start();
		}

		if(c.getTotalNumberItems() == 1) {
			model.addElement(c.getName() + " " + c.getTotalNumberItems() + " item");
			//System.out.println(c.getName() + "is at " + model.indexOf(c.getName() + " " + c.getTotalNumberItems() + " item"));
		} else {
			model.addElement(c.getName() + " " + c.getTotalNumberItems() + " items");
			//System.out.println(c.getName() + "is at " + model.indexOf(c.getName() + " " + c.getTotalNumberItems() + " items"));
		}

		
	}
	
}
