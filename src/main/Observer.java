package main;

public interface Observer {
	public void update();
	public void updateDone();
	public void updateServed(Customer c);
}
