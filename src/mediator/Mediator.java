package mediator;

import java.util.HashMap;

import model.User;

public class Mediator {

	private HashMap<String, User> users;
	
	public Mediator() {
		users = new HashMap<>();
	}
	
	public void addUser(User user) {
		users.put(user.getId(), user);
	}
	
	public void send(String id, String message) {
		User user = users.get(id);
		if(user != null) {
			user.send(id, message);
		}
	}

}
