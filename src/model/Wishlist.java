package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBConnection;

public class Wishlist {

	private String Wishlist_id;
	private String Item_id;
	private String User_id;
	private static Connection con;
	
	private static Connection getConnection() {
		if(con == null) {
			con = DBConnection.getInstance().getConnnection();
		}
		return con;
	}
	
	public Wishlist(String Wishlist_id, String Item_id, String User_id) {
		super();
		this.Wishlist_id = Wishlist_id;
		this.Item_id = Item_id;
		this.User_id = User_id;
	}

	public String getId() {
		return Wishlist_id;
	}

	public void setId(String Wishlist_id) {
		this.Wishlist_id = Wishlist_id;
	}

	public String getItemId() {
		return Item_id;
	}

	public void setItemId(String Item_id) {
		this.Item_id = Item_id;
	}

	public String getUserId() {
		return User_id;
	}

	public void setUserId(String User_id) {
		this.User_id = User_id;
	}
	
	public static ArrayList<Item> ViewWishlist(String userId2) {
		
		getConnection();

		try {
			String query = "SELECT item.Item_id, item.User_id, item.name, item.category, item.size, item.price, item.status, item.offer, item.updatedBy FROM wishlist JOIN item ON wishlist.Item_id = item.Item_id WHERE wishlist.User_id = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, userId2);
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
	
	public static boolean AddWishlist(String itemId, String userId2) {
		
		getConnection();
		
		try {
			String query = "INSERT INTO wishlist (Item_id, User_id) VALUES (?, ?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, itemId);
			st.setString(2, userId2);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public static boolean RemoveWishlist(String itemId, String userId2) {
		
		getConnection();
		
		try {
			String query = "DELETE FROM wishlist WHERE Item_id = ? AND User_id = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, itemId);
			st.setString(2, userId2);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public static boolean RemoveWishlist(String itemId) {
		
		getConnection();
		
		try {
			String query = "DELETE FROM wishlist WHERE Item_id = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, itemId);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
}
