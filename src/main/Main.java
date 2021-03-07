package main;

public class Main {
    public static void main (String [] args) {
    	Queue queue = new Queue();

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
    }
}