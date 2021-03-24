package main;

import java.awt.BorderLayout;
import java.awt.Point;

import javax.swing.*;

@SuppressWarnings("serial")
public class QueueGUI extends JFrame implements Observer2{
	JLabel label, label2;
	Queue<Customer> queue;
	DefaultListModel<String> model = new DefaultListModel<>();
	protected static Integer count = 0;
	protected static Point point;
	public QueueGUI(Queue<Customer> queue) {
		QueueGUI.count++;
		this.queue = queue;
		
		this.setSize(600, 350);
        this.setTitle("Queue");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        label = new JLabel("There are currently no people waiting in the queue.");
        label2 = new JLabel("Serving:");
        
        JPanel top = new JPanel(new BorderLayout());
        top.add(label, BorderLayout.PAGE_START);
        top.add(label2, BorderLayout.PAGE_END);
        this.add(top, BorderLayout.PAGE_START);
        

        JList<String> list = new JList<>(model);

        add(list);
        
        setVisible(true);
		QueueGUI.point = this.getLocationOnScreen();
	}
	@Override
	public void update(Customer c) {
		if (!queue.isTerminated()) {
			label.setText("There are currently " + queue.size() + " people waiting in the queue.");	
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
