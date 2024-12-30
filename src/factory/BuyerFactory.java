package factory;

import mediator.Mediator;
import model.Buyer;
import model.User;

public class BuyerFactory implements IUserFactory {

	@Override
	public User createUser(String User_id, String username, String password, String phoneNumber, String address, String role, int balance, Mediator mediator) {
		return new Buyer(User_id, username, password, phoneNumber, address, role, balance, mediator);
	}

}
