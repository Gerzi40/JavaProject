package model;

import mediator.Mediator;
import observer.Subscriber;

public class Buyer extends User implements Subscriber {

	public Buyer(String User_id, String username, String password, String phoneNumber, String address, String role, int balance, Mediator mediator) {
		super(User_id, username, password, phoneNumber, address, role, balance, mediator);
	}

	@Override
	public void getNotified(String message) {
		System.out.println(this.getUsername() + " is notified");
		System.out.println("Message: " + message);
	}

}
