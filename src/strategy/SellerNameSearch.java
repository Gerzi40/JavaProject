package strategy;

import controller.UserController;
import javafx.collections.ObservableList;
import model.User;

public class SellerNameSearch implements SearchStrategy<User> {

	@Override
	public ObservableList<User> search(String query) {
		return UserController.getSellerByUsername(query);
	}
	
}
