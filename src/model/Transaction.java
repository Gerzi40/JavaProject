package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBConnection;

public class Transaction {

	private String Transaction_id;
	private String User_id;
	private String Item_id;
	private static Connection con;
	
	private static Connection getConnection() {
		if(con == null) {
			con = DBConnection.getInstance().getConnnection();
		}
		return con;
	}
	
	public Transaction(String Transaction_id, String User_id, String Item_id) {
		super();
		this.Transaction_id = Transaction_id;
		this.User_id = User_id;
		this.Item_id = Item_id;
	}

	public String getId() {
		return Transaction_id;
	}

	public void setId(String Transaction_id) {
		this.Transaction_id = Transaction_id;
	}

	public String getUserId() {
		return User_id;
	}

	public void setUserId(String User_id) {
		this.User_id = User_id;
	}

	public String getItemId() {
		return Item_id;
	}

	public void setItemId(String Item_id) {
		this.Item_id = Item_id;
	}
	
	public static boolean PurchaseItems(String userId2, String itemId) {
		
		getConnection();
		
		try {
			String query = "INSERT INTO transaction (User_id, Item_id) VALUES (?, ?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, userId2);
			st.setString(2, itemId);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public static ArrayList<Item> ViewHistory(String User_id) {
		
		getConnection();

		try {
			String query = "SELECT item.Item_id, item.User_id, item.name, item.category, item.size, item.price, item.status, item.offer, item.updatedBy FROM transaction JOIN item ON transaction.Item_id = item.Item_id WHERE transaction.User_id = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, User_id);
			ResultSet result = st.executeQuery();

			ArrayList<Item> itemList = new ArrayList<>();
			while (result.next()) {
				Item item = new Item(result.getString("Item_id"), result.getString("User_id"), result.getString("name"),
						result.getString("category"), result.getString("size"), result.getString("price"),
						result.getString("status"), result.getString("offer"), result.getString("updatedBy"));
				itemList.add(item);
			}

			return itemList;

		} catch (Exception e) {
			return null;
		}
		
	}
	
}
