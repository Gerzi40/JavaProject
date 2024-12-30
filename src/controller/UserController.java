package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import model.User;
import util.Session;

public class UserController {

	public static boolean Login(
			String username,
			String password,
			Label usernameErrorMessage,
			Label passwordErrorMessage) {
		return isLoginInputValid(
				username,
				password,
				usernameErrorMessage,
				passwordErrorMessage);
	}
	
	private static boolean isLoginInputValid(
			String username,
			String password,
			Label usernameErrorMessage,
			Label passwordErrorMessage
	) {
		
		boolean result = true;
		
		if(username.isEmpty()) {
			usernameErrorMessage.setManaged(true);
			usernameErrorMessage.setText("Must be filled");
			result = false;
		} else {
			usernameErrorMessage.setManaged(false);
			usernameErrorMessage.setText("");
		}
		
		if(password.isEmpty()) {
			passwordErrorMessage.setManaged(true);
			passwordErrorMessage.setText("Must be filled");
			result = false;
		} else {
			passwordErrorMessage.setManaged(false);
			passwordErrorMessage.setText("");
		}
		
		// can`t be empty
		if(result == false) return false;
		
		// check for role admin
		if(username.equals("admin") && password.equals("admin")) {
			Session.setUserRole("admin");
			return true;
		}
		
		User userData = User.getUserByUsername(username);
		if(userData == null) {
			usernameErrorMessage.setManaged(true);
			usernameErrorMessage.setText("Username not found");
			return false;
		}
		
		if(!userData.getPassword().equals(password)) {
			passwordErrorMessage.setManaged(true);
			passwordErrorMessage.setText("Wrong password");
			return false;
		}
		
		Session.setUserId(userData.getId());
		Session.setUserRole(userData.getRole());
		
		return true;
		
	}
	
	public static boolean Register(
			String username,
			String password,
			String phoneNumber,
			String address,
			String role,
			Label usernameErrorMessage,
			Label passwordErrorMessage,
			Label phoneNumberErrorMessage,
			Label addressErrorMessage,
			Label roleErrorMessage) {
		if(CheckAccountValidation(
				username,
				password,
				phoneNumber,
				address,
				role,
				usernameErrorMessage,
				passwordErrorMessage,
				phoneNumberErrorMessage,
				addressErrorMessage,
				roleErrorMessage)
		) {			
			return User.Register(username, password, phoneNumber, address, role);
		}
		
		return false;
	}
	
	private static boolean CheckAccountValidation(
			String username,
			String password,
			String phoneNumber,
			String address,
			String role,
			Label usernameErrorMessage,
			Label passwordErrorMessage,
			Label phoneNumberErrorMessage,
			Label addressErrorMessage,
			Label roleErrorMessage
	) {
		
		boolean result = true;
		
		if(username.isEmpty()) {
			usernameErrorMessage.setManaged(true);
			usernameErrorMessage.setText("Must be filled");
			result = false;
		} else {
			usernameErrorMessage.setManaged(false);
			usernameErrorMessage.setText("");
		}
		
		if(password.isEmpty()) {
			passwordErrorMessage.setManaged(true);
			passwordErrorMessage.setText("Must be filled");
			result = false;
		} else {
			passwordErrorMessage.setManaged(false);
			passwordErrorMessage.setText("");
		}
		
		if(phoneNumber.isEmpty()) {
			phoneNumberErrorMessage.setManaged(true);
			phoneNumberErrorMessage.setText("Must be filled");
			result = false;
		} else {
			phoneNumberErrorMessage.setManaged(false);
			phoneNumberErrorMessage.setText("");
		}
		
		if(address.isEmpty()) {
			addressErrorMessage.setManaged(true);
			addressErrorMessage.setText("Must be filled");
			result = false;
		} else {
			addressErrorMessage.setManaged(false);
			addressErrorMessage.setText("");
		}
		
		if(role.isEmpty()) {
			roleErrorMessage.setManaged(true);
			roleErrorMessage.setText("Must be filled");
			result = false;
		} else {
			roleErrorMessage.setManaged(false);
			roleErrorMessage.setText("");
		}
		
		// tidak boleh ada yang kosong
		if(result == false) return false;
		
		if(username.length() < 3) {
			usernameErrorMessage.setManaged(true);
			usernameErrorMessage.setText("Minimum length 3");
			result = false;
		} else {			
			User userData = User.getUserByUsername(username);
			if(userData != null && userData.getUsername().equals(username)) {
				usernameErrorMessage.setManaged(true);
				usernameErrorMessage.setText("Username already existed");
				result = false;
			}
		}
		
		
		if(password.length() < 8) {
			passwordErrorMessage.setManaged(true);
			passwordErrorMessage.setText("Minimum length 8");
			result = false;
		} else if(!(password.indexOf("!") != -1 ||
				password.indexOf("@") != -1 ||
				password.indexOf("#") != -1 ||
				password.indexOf("$") != -1 ||
				password.indexOf("%") != -1 ||
				password.indexOf("^") != -1 ||
				password.indexOf("&") != -1 ||
				password.indexOf("*") != -1)
		) {
			passwordErrorMessage.setManaged(true);
			passwordErrorMessage.setText("Must contain special character");
			result = false;
		}
		
		if(!phoneNumber.startsWith("+62")) {
			phoneNumberErrorMessage.setManaged(true);
			phoneNumberErrorMessage.setText("Must start with +62");
			result = false;
		} else if(phoneNumber.length() != 12) {
			phoneNumberErrorMessage.setManaged(true);
			phoneNumberErrorMessage.setText("Phone Number can't be more than 10 digit");
			result = false;
		} else {			
			String remainingPhoneNumber = phoneNumber.substring(3);
			try {
				Long.parseLong(remainingPhoneNumber);
			} catch (Exception e) {
				phoneNumberErrorMessage.setManaged(true);
				phoneNumberErrorMessage.setText("Must only contain number");
				result = false;
			}
		}
		
		return result;
		
	}

	public static ObservableList<User> getSellerByUsername(String username) {
		ArrayList<User> userList = User.getSellerByUsername(username);
		return FXCollections.observableArrayList(userList);
	}
	
	public static int getUserBalance(String userId) {
		return User.getUserBalance(userId);
	}
	
	public static boolean updateBuyerSellerBalance(String buyerId, String sellerId, int amount) {
		
		int buyerBalance = getUserBalance(buyerId);
		int sellerBalance = getUserBalance(sellerId);
		
		return User.updateUserBalance(buyerId, buyerBalance - amount) && User.updateUserBalance(sellerId, sellerBalance + amount);
	}
	
}
