package model;

import java.util.ArrayList;

import mediator.Mediator;
import observer.Publisher;
import observer.Subscriber;

public class Seller extends User implements Publisher {

	private ArrayList<Subscriber> subscribers;
	
	public Seller(String User_id, String username, String password, String phoneNumber, String address, String role, int balance, Mediator mediator) {
		super(User_id, username, password, phoneNumber, address, role, balance, mediator);
	}

	@Override
	public void addSubscriber(Subscriber subscriber) {
		subscribers.add(subscriber);
	}

	@Override
	public void removeSubscriber(Subscriber subscriber) {
		subscribers.remove(subscriber);		
	}

	@Override
	public void notifySubscriber(String message) {
		for(Subscriber subscriber : subscribers) {
			subscriber.getNotified(message);
		}
	}

}
