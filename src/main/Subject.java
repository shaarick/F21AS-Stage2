package main;

public interface Subject {
	public void registerObserver(Observer observer);
	public void removeObserver(Observer observer);
	public void notifyObservers();
	public void notifyDone();
	public void notifyServed(Customer c);
}
