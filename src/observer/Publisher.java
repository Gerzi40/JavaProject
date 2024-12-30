package observer;

public interface Publisher {

	public void addSubscriber(Subscriber subscriber);
	public void removeSubscriber(Subscriber subscriber);
	public void notifySubscriber(String message);
	
}
