package main;

import java.awt.event.*;

public class QueueController implements ActionListener {
	private QueueGUI gui;
	
	public QueueController(QueueGUI q) {
		gui = q;
        gui.increaseTime.addActionListener(this);
        gui.decreaseTime.addActionListener(this);
        gui.qincrease.addActionListener(this);
        gui.qdecrease.addActionListener(this);
        gui.rincrease.addActionListener(this);
        gui.rdecrease.addActionListener(this);
	}

	public void actionPerformed(ActionEvent event) {
		if(event.getSource()==gui.increaseTime) {
			CustomerList.setTime(CustomerList.getTime()+100);
			gui.time.setText(CustomerList.getTime() / 1000.0 + "s");
	        LogClass.logger.info("Serving Time Increased");
			if(CustomerList.getTime()>=5000) {
				gui.increaseTime.setEnabled(false);
			}
			if(!gui.decreaseTime.isEnabled())
				gui.decreaseTime.setEnabled(true);
		}
		
		if(event.getSource()==gui.decreaseTime) {
			CustomerList.setTime(CustomerList.getTime()-100);
			gui.time.setText(CustomerList.getTime() / 1000.0 + "s");
	        LogClass.logger.info("Serving Time Decreased");
			if(CustomerList.getTime()<=100) {
				gui.decreaseTime.setEnabled(false);
			}
			if(!gui.increaseTime.isEnabled())
				gui.increaseTime.setEnabled(true);
		}
		
		if(event.getSource()==gui.qincrease) {
			CustomerList.setQTime(CustomerList.getQTime()+100);
			gui.qtime.setText(CustomerList.getQTime() / 1000.0 + "s");
	        LogClass.logger.info("Enqueue Time Increased");
			if(CustomerList.getQTime()>=5000) {
				gui.qincrease.setEnabled(false);
			}
			if(!gui.qdecrease.isEnabled())
				gui.qdecrease.setEnabled(true);
		}
		
		if(event.getSource()==gui.qdecrease) {
			CustomerList.setQTime(CustomerList.getQTime()-100);
			gui.qtime.setText(CustomerList.getQTime() / 1000.0 + "s");
	        LogClass.logger.info("Enqueue Time Decreased");
			if(CustomerList.getQTime()<=100) {
				gui.qdecrease.setEnabled(false);
			}
			if(!gui.qincrease.isEnabled())
				gui.qincrease.setEnabled(true);
		}
		
		if(event.getSource()==gui.rincrease) {
			CustomerList.setRTime(CustomerList.getRTime()+100);
			gui.rtime.setText(CustomerList.getRTime() / 1000.0 + "s");
	        LogClass.logger.info("Resting Time Increased");
			if(CustomerList.getRTime()>=5000) {
				gui.rincrease.setEnabled(false);
			}
			if(!gui.rdecrease.isEnabled())
				gui.rdecrease.setEnabled(true);
		}
		
		if(event.getSource()==gui.rdecrease) {
			CustomerList.setRTime(CustomerList.getRTime()-100);
			gui.rtime.setText(CustomerList.getRTime() / 1000.0 + "s");
	        LogClass.logger.info("Resting Time Decreased");
			if(CustomerList.getRTime()<=100) {
				gui.rdecrease.setEnabled(false);
			}
			if(!gui.rincrease.isEnabled())
				gui.rincrease.setEnabled(true);
		}
		
	}
}
