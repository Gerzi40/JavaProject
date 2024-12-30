package factory;

import mediator.Mediator;
import model.Seller;
import model.User;

public class SellerFactory implements IUserFactory {

	@Override
	public User createUser(String User_id, String username, String password, String phoneNumber, String address, String role, int balance, Mediator mediator) {
		return new Seller(User_id, username, password, phoneNumber, address, role, balance, mediator);
	}

}
