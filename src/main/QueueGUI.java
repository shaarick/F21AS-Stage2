package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class QueueGUI extends JFrame implements Observer2, ActionListener{
	JLabel label, label2, time;
	JButton increaseTime, decreaseTime;
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
        time = new JLabel(Main.getTime()+"",JLabel.CENTER);
        increaseTime = new JButton("Increase Time");
        increaseTime.addActionListener(this);
        decreaseTime = new JButton("Decrease Time");
        decreaseTime.addActionListener(this);
        
        JPanel top = new JPanel(new BorderLayout());
        top.add(label, BorderLayout.PAGE_START);
        top.add(label2, BorderLayout.PAGE_END);
        this.add(top, BorderLayout.PAGE_START);
        

        JList<String> list = new JList<>(model);
		JScrollPane scroll = new JScrollPane(list);
		this.add(scroll);

        JPanel bottom = new JPanel(new GridLayout(1,3));
        bottom.add(decreaseTime);
        bottom.add(time);
        bottom.add(increaseTime);
        this.add(bottom, BorderLayout.PAGE_END);
        
        setVisible(true);
		QueueGUI.point = this.getLocationOnScreen();
	}
	@Override
	public void update(Customer c) {
		label.setText("There are currently " + queue.size() + " people waiting in the queue.");	
		if((queue.size() >1) & (StaffGUI.count < 4)) {
			String name = "Server " + (StaffGUI.count + 1);
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
	
	@Override
	public void update() {
		if (queue.getDone() == true) {
			label.setText("Finished serving all customers in the queue.");
		} 
	}

	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==increaseTime) {
			Main.setTime(Main.getTime()+100);
			time.setText(Main.getTime()+"");
			if(Main.getTime()>=5000) {
				increaseTime.setEnabled(false);
			}
			if(!decreaseTime.isEnabled())
				decreaseTime.setEnabled(true);
		}
		
		if(event.getSource()==decreaseTime) {
			Main.setTime(Main.getTime()-100);
			time.setText(Main.getTime()+"");
			if(Main.getTime()<=100) {
				decreaseTime.setEnabled(false);
			}
			if(!increaseTime.isEnabled())
				increaseTime.setEnabled(true);
		}
	}
	
}
