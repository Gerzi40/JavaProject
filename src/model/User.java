package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import factory.BuyerFactory;
import factory.SellerFactory;
import mediator.Mediator;
import util.DBConnection;

public abstract class User {

	private String User_id;
	private String username;
	private String password;
	private String phoneNumber;
	private String address;
	private String role;
	private int balance;
	private static Connection con;
	private Mediator mediator;
	
	private static Connection getConnection() {
		if(con == null) {
			con = DBConnection.getInstance().getConnnection();
		}
		return con;
	}
	
	public void send(String id, String message) {
		mediator.send(id, message);
	}
	
	public User(String User_id, String username, String password, String phoneNumber, String address, String role, int balance, Mediator mediator) {
		super();
		this.User_id = User_id;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.role = role;
		this.balance = balance;
		this.mediator = mediator;
	}
	
	public String getId() {
		return User_id;
	}
	
	public void setId(String User_id) {
		this.User_id = User_id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public static boolean Register(String username, String password, String phoneNumber, String address, String role) {
		
		getConnection();
		
		try {
			String query = "INSERT INTO user (username, password, phoneNumber, address, role, balance) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, username);
			st.setString(2, password);
			st.setString(3, phoneNumber);
			st.setString(4, address);
			st.setString(5, role);
			if(role.equals("Buyer")) {
				st.setInt(6, 100);
			} else if(role.equals("Seller")) {
				st.setInt(6, 0);				
			}
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public static User getUserByUsername(String username) {
		
		getConnection();
		
		try {
			String query = "SELECT User_id, username, password, role, balance FROM user WHERE username = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, username);
			ResultSet result = st.executeQuery();
			
			User userData = null;
			while(result.next()) {
				User user;
				if(result.getString("role").equals("Buyer")) {					
					user = new BuyerFactory().createUser(result.getString("User_id"), result.getString("username"), result.getString("password"), "", "", result.getString("role"), result.getInt("balance"), null);
				} else {
					user = new SellerFactory().createUser(result.getString("User_id"), result.getString("username"), result.getString("password"), "", "", result.getString("role"), result.getInt("balance"), null);
				}
				userData = user;
				break;
			}
			
			return userData;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public static ArrayList<User> getSellerByUsername(String username) {
		
		getConnection();
		
		try {
			String query = "SELECT User_id, username, password, role FROM user WHERE role = ? AND username = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, "Seller");
			st.setString(2, username);
			ResultSet result = st.executeQuery();
			
			ArrayList<User> userList = new ArrayList<>();
			while(result.next()) {
				User user;
				if(result.getString("role").equals("Buyer")) {					
					user = new BuyerFactory().createUser(result.getString("User_id"), result.getString("username"), result.getString("password"), "", "", result.getString("role"), result.getInt("balance"), null);
				} else {
					user = new SellerFactory().createUser(result.getString("User_id"), result.getString("username"), result.getString("password"), "", "", result.getString("role"), result.getInt("balance"), null);
				}
				userList.add(user);
			}
			
			return userList;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	public static int getUserBalance(String userId) {
		
		getConnection();
		
		try {
			String query = "SELECT balance FROM user WHERE User_id = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, userId);
			ResultSet result = st.executeQuery();
			
			while(result.next()) {
				return result.getInt("balance");
			}
			
			return -1;
		} catch (Exception e) {
			return -1;
		}
		
	}
	
	public static boolean updateUserBalance(String userId, int balance) {
		
		getConnection();
		
		try {
			String query = "UPDATE user SET balance = ? WHERE User_id = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, balance);
			st.setString(2, userId);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
}
