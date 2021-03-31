package main;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.sun.org.apache.xpath.internal.operations.And;
/**
 * QueueGUI Class 
 * Displays the Queue of customers waiting to be served 
 * as well as ones finished serving.
 * 
 * @author Shariq Farooqui  - msf2000 - H00358466
 *
 */
@SuppressWarnings("serial")
public class QueueGUI extends JFrame {
	
	protected static JLabel label;
	private JLabel label2, label3;
	protected JLabel time, qtime, rtime;
	protected JButton increaseTime, decreaseTime;
	protected JButton qincrease, qdecrease;
	protected JButton rincrease, rdecrease;
	protected static Integer qCount = 0;
	protected static DefaultListModel<String> model = new DefaultListModel<>();
	protected static DefaultListModel<String> model2 = new DefaultListModel<>();
	protected static Queue<Customer> Q;

	/**
	 * Creates the QueueGUI window.
	 * Report is generated on exit.
	 */
	public QueueGUI() {
		
		this.setSize(600, 350);
        this.setTitle("Queue");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Main.writeReport();
            }
        });
		
        // Label indicating Queue size and serving status.
        QueueGUI.label = new JLabel("There are currently no people waiting in the queue.", SwingConstants.CENTER);
        label2 = new JLabel("Serving:",SwingConstants.CENTER);
        label3 = new JLabel("Finished Serving:",SwingConstants.CENTER);
        
        // Adding labels to a panel
        JPanel top = new JPanel(new BorderLayout());
        top.add(QueueGUI.label, BorderLayout.PAGE_START);
        top.add(label2, BorderLayout.WEST);
        top.add(label3, BorderLayout.EAST);
        getContentPane().add(top, BorderLayout.PAGE_START);
        
        // GridLayout with multiple rows and 2 columns.
        // Add serving list (with scrollbar) on one side.
        JPanel middle = new JPanel(new GridLayout(0,2));
        JList<String> list = new JList<>( QueueGUI.model);
		JScrollPane scroll = new JScrollPane(list);
		middle.add(scroll);
		
		// Add served list (with scrollbar) on one side.
		JList<String> list2 = new JList<>( QueueGUI.model2);
		JScrollPane scroll2 = new JScrollPane(list2);
		middle.add(scroll2);
		this.add(middle);
		
		/* Following 9 labels are labels for the time settings used to alter
		 * speed of simulation.
		 */
		time = new JLabel(CustomerList.getTime() / 1000.0 + "s",JLabel.CENTER);
        increaseTime = new JButton("Increase Serving Time");
        decreaseTime = new JButton("Decrease Serving Time");
        
        qtime = new JLabel(CustomerList.getQTime() / 1000.0 + "s",JLabel.CENTER);
        qincrease = new JButton("Increase Enqueue Time");
        qdecrease = new JButton("Decrease Enqueue Time");
        
        rtime = new JLabel(CustomerList.getRTime() / 1000.0 + "s",JLabel.CENTER);
        rincrease = new JButton("Increase Resting Time");
        rdecrease = new JButton("Decrease Resting Time");
        
        // Adding buttons to a panel
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
        
        // Adding panel to this frame
        getContentPane().add(bottom, BorderLayout.PAGE_END);
        
        setVisible(true);
        LogClass.logger.info("GUI Initialised");
	}
	
	/**
	 * Adds customer to the serving list by updating the serving model. 
	 * @param c Customer to be added
	 */
	public synchronized static void update(Customer c) {
		// Add item if single item or items for multiple ones
		if(c.getTotalNumberItems() == 1) {
			 QueueGUI.model.addElement(c.getName() + " " + c.getTotalNumberItems() + " item");
		} else {
			 QueueGUI.model.addElement(c.getName() + " " + c.getTotalNumberItems() + " items");
		}
		// Increase queue count
		QueueGUI.incQ();
		// Update queue count on GUI
		QueueGUI.label.setText("There are currently " + QueueGUI.qCount + " people waiting in the queue.");
		
		/*
		 * If queue count is more than or equal to 4 AND we have less then 3 servers,
		 * we create another server thread.
		 */
		if((QueueGUI.qCount >= 4) & (StaffGUI.count < 3)) {
			String name = "Server " + (StaffGUI.count + 1);
			Staff three = new Staff(name, QueueGUI.Q);
			Thread serverThree = new Thread(three, name);
			serverThree.start();
	        LogClass.logger.info("Third Server Added");
		}
		
	}
	
	/**
	 * Increase queue count.
	 */
	public static synchronized void incQ() {
		QueueGUI.qCount++ ;
	}
	
	/**
	 * Decrease queue count.
	 */
	public static synchronized void decQ() {
		QueueGUI.qCount-- ;
	}
	
	/**
	 * Once a customer is finished being served, this method is called and 
	 * we transfer them from serving list (model) to served list (model2). 
	 * @param c Customer to be moved
	 */
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
	
}
