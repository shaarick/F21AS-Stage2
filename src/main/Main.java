package main;

public class Main {
    public static void main (String [] args) throws InterruptedException {
    	Queue<Customer> queue = new Queue<Customer>();

    	//Initializing a CustomerList
    	CustomerList customerList = new CustomerList(queue);
    	//Initializing an Input
    	Input i = new Input(customerList);
    	//Reading the OrdersList and filling up the customerList
    	i.readFile("OrdersList.txt");
    	
    	
    	//Starting up the producer thread
    	//and passing the customers from the customerList
    	//to the queue
    	Thread producerThread = new Thread(customerList);
    	producerThread.start();
    	//customerList.run();
    	
    	
    	Staff one = new Staff(queue);
    	Thread serverOne = new Thread(one, "Server 1");
    	serverOne.start();
    	
    	Staff two = new Staff(queue);
    	Thread serverTwo = new Thread(two, "Server 2");
    	serverTwo.start();
    }
}
