package factory;

import mediator.Mediator;
import model.User;

public interface IUserFactory {

	public User createUser(String User_id, String username, String password, String phoneNumber, String address, String role, int balance, Mediator mediator);
	
}
