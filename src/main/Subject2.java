package main;

public interface Subject2 {
	public void registerObserver(Observer2 observer);
	public void removeObserver(Observer2 observer);
	public void notifyObservers(Customer c);
}
