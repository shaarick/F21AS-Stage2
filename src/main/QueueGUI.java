package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class QueueGUI extends JFrame implements ActionListener{
	
	protected static JLabel label;
	private JLabel label2, label3, time, qtime, rtime;
	private JButton increaseTime, decreaseTime;
	private JButton qincrease, qdecrease;
	private JButton rincrease, rdecrease;
	protected static Integer qCount = 0;
	protected static DefaultListModel<String> model = new DefaultListModel<>();
	protected static DefaultListModel<String> model2 = new DefaultListModel<>();
	protected static Queue<Customer> Q;

	public QueueGUI() {
		
		this.setSize(600, 350);
        this.setTitle("Queue");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Main.writeReport();
            }
        });
		
        QueueGUI.label = new JLabel("There are currently no people waiting in the queue.", SwingConstants.CENTER);
        label2 = new JLabel("Serving:",SwingConstants.CENTER);
        label3 = new JLabel("Finished Serving:",SwingConstants.CENTER);
        
        JPanel top = new JPanel(new BorderLayout());
        top.add(QueueGUI.label, BorderLayout.PAGE_START);
        top.add(label2, BorderLayout.WEST);
        top.add(label3, BorderLayout.EAST);
        getContentPane().add(top, BorderLayout.PAGE_START);
        
        JPanel middle = new JPanel(new GridLayout(0,2));
        JList<String> list = new JList<>( QueueGUI.model);
		JScrollPane scroll = new JScrollPane(list);
		middle.add(scroll);
		
		JList<String> list2 = new JList<>( QueueGUI.model2);
		JScrollPane scroll2 = new JScrollPane(list2);
		middle.add(scroll2);
		this.add(middle);
		
		time = new JLabel(Main.getTime()+"",JLabel.CENTER);
        increaseTime = new JButton("Increase Serving Time");
        increaseTime.addActionListener(this);
        decreaseTime = new JButton("Decrease Serving Time");
        decreaseTime.addActionListener(this);
        
        qtime = new JLabel(Main.getQTime()+"",JLabel.CENTER);
        qincrease = new JButton("Increase Enqueue Time");
        qincrease.addActionListener(this);
        qdecrease = new JButton("Decrease Enqueue Time");
        qdecrease.addActionListener(this);
        
        rtime = new JLabel(Main.getRTime()+"",JLabel.CENTER);
        rincrease = new JButton("Increase Resting Time");
        rincrease.addActionListener(this);
        rdecrease = new JButton("Decrease Resting Time");
        rdecrease.addActionListener(this);
        
        JPanel bottom = new JPanel(new GridLayout(3,3));
        bottom.add(qtime);
        bottom.add(time);
        bottom.add(rtime);
        bottom.add(qincrease);
        bottom.add(increaseTime);
        bottom.add(rincrease);
        bottom.add(qdecrease);
        bottom.add(decreaseTime);
        bottom.add(rdecrease);

        getContentPane().add(bottom, BorderLayout.PAGE_END);
        
        setVisible(true);
	}

	public synchronized static void update(Customer c) {
		
		if(c.getTotalNumberItems() == 1) {
			 QueueGUI.model.addElement(c.getName() + " " + c.getTotalNumberItems() + " item");
		} else {
			 QueueGUI.model.addElement(c.getName() + " " + c.getTotalNumberItems() + " items");
		}
		QueueGUI.incQ();
		QueueGUI.label.setText("There are currently " + QueueGUI.qCount + " people waiting in the queue.");
		
		if((QueueGUI.qCount > 2) & (StaffGUI.count < 3)) {
			String name = "Server " + (StaffGUI.count + 1);
			Staff three = new Staff(name, QueueGUI.Q);
			Thread serverThree = new Thread(three, name);
			serverThree.start();
		}
		
	}
	
	public static synchronized void incQ() {
		QueueGUI.qCount++ ;
	}
	
	public static synchronized void decQ() {
		QueueGUI.qCount-- ;
	}
	
	public synchronized static void transfer(Customer c) {
			String str;
			if(c.getTotalNumberItems() == 1) {
				str = c.getName() + " " + c.getTotalNumberItems() + " item";
			} else {
				str = c.getName() + " " + c.getTotalNumberItems() + " items";
			}
			
			QueueGUI.model.removeElement(str);
			QueueGUI.model2.addElement(c.getName());
			QueueGUI.decQ();
			if (QueueGUI.qCount == 0) {
				QueueGUI.label.setText("Finished serving all customers in the queue.");	
			} else {
				QueueGUI.label.setText("There are currently " + QueueGUI.qCount + " people waiting in the queue.");	
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
		
		if(event.getSource()==qincrease) {
			Main.setQTime(Main.getQTime()+100);
			qtime.setText(Main.getQTime()+"");
			if(Main.getQTime()>=5000) {
				qincrease.setEnabled(false);
			}
			if(!qdecrease.isEnabled())
				qdecrease.setEnabled(true);
		}
		
		if(event.getSource()==qdecrease) {
			Main.setQTime(Main.getQTime()-100);
			qtime.setText(Main.getQTime()+"");
			if(Main.getQTime()<=100) {
				qdecrease.setEnabled(false);
			}
			if(!qincrease.isEnabled())
				qincrease.setEnabled(true);
		}
		
		if(event.getSource()==rincrease) {
			Main.setRTime(Main.getRTime()+100);
			rtime.setText(Main.getRTime()+"");
			if(Main.getRTime()>=5000) {
				rincrease.setEnabled(false);
			}
			if(!rdecrease.isEnabled())
				rdecrease.setEnabled(true);
		}
		
		if(event.getSource()==rdecrease) {
			Main.setRTime(Main.getRTime()-100);
			rtime.setText(Main.getRTime()+"");
			if(Main.getRTime()<=100) {
				rdecrease.setEnabled(false);
			}
			if(!rincrease.isEnabled())
				rincrease.setEnabled(true);
		}
		
	}
	
}
