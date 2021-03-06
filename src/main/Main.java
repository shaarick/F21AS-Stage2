package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;

public class Main {
	public static LogClass log;
	public static CustomerList customerList;
	private final static Path p = Paths.get("Report.txt").toAbsolutePath(); //stores the path (works on any system)
	
	public static void main (String [] args) throws InterruptedException {
		
		
		log = new LogClass();
		Queue<Customer> queue = new Queue<Customer>();
		//Initializing a CustomerList
		customerList = new CustomerList(queue);
		//Initializing an Input
		Input i = new Input(customerList);
		//Reading the OrdersList and filling up the customerList
		i.readFile("OrdersList.txt");
		
		//Starting the QueueGUI
		QueueGUI q = new QueueGUI();
		QueueGUI.Q = queue;
		QueueController controller = new QueueController(q);
		//Starting up the producer thread
		//and passing the customers from the customerList
		//to the queue
		Thread producerThread = new Thread(customerList);
		producerThread.start();
		//customerList.run();


		Staff one = new Staff("Server 1", queue);
		Thread serverOne = new Thread(one, "Server 1");
		serverOne.start();

		Staff two = new Staff("Server 2", queue);
		Thread serverTwo = new Thread(two, "Server 2");
		serverTwo.start();
		
	}

	public static void writeReport() {
		Set <Customer> listOfCustomers = customerList.getCustomerList();
		double GlobalNetIncome =0.0;
		
		try(FileWriter fw = new FileWriter(p.toString(), false); //FALSE MEANS RE WRITE
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw))
		{
			for (Iterator iterator = listOfCustomers.iterator(); iterator.hasNext();) {
				Customer customer = (Customer) iterator.next();
				out.write(customer.getName() + " " + "Order: \n");
				
				for (Iterator iterator2 = customer.getItemsOrdered().iterator(); iterator2.hasNext();) {
					Item item = (Item) iterator2.next();
					out.write(item.getName() +": \t");
					out.write(Double.toString(item.getPrice()) + "\n");
				}
				out.write("TOTAL AMOUNT OF ORDER (inc Discounts): " + Double.toString(customer.getTotalOrderAmount()) + "\n");
				out.write("--------------------------------------------------------------------------------- \n");
				GlobalNetIncome += customer.getTotalOrderAmount();
			}
			out.write("GLOBAL NET INCOME == " + GlobalNetIncome);
		}
		catch (IOException e) {
			System.err.println("CANT WRITE TO FILE, DOESNT EXIST");
			e.printStackTrace();
		}
	}
}
