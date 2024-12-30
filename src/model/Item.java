package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBConnection;

public class Item {

	private String Item_id;
	private String User_id;
	private String name;
	private String category;
	private String size;
	private String price;
	private String status;
	private boolean isWishlistButtonDisabled;
	private String offer;
	private String updatedBy;
	
	private static Connection con;
	
	private static Connection getConnection() {
		if(con == null) {
			con = DBConnection.getInstance().getConnnection();
		}
		return con;
	}

	public Item(String i, String j, String name, String category, String size, String k, String status,
			String l, String m) {
		this.Item_id = i;
		this.User_id = j;
		this.name = name;
		this.category = category;
		this.size = size;
		this.price = k;
		this.status = status;
		this.offer = l;
		this.updatedBy = m;
	}

	public String getId() {
		return Item_id;
	}

	public void setId(String Item_id) {
		this.Item_id = Item_id;
	}

	public String getUserId() {
		return User_id;
	}

	public void setUserId(String User_id) {
		this.User_id = User_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean getIsWishlistButtonDisabled() {
		return isWishlistButtonDisabled;
	}

	public void setIsWishlistButtonDisabled(boolean isWishlistButtonDisabled) {
		this.isWishlistButtonDisabled = isWishlistButtonDisabled;
	}

	public String getOffer() {
		return offer;
	}

	public void setOffer(String offer) {
		this.offer = offer;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public static boolean UploadItem(String userId, String name, String category, String size, String price) {

		getConnection();

		try {
			String query = "INSERT INTO item (User_id, name, category, size, price, status, offer, updatedBy) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, userId);
			st.setString(2, name);
			st.setString(3, category);
			st.setString(4, size);
			st.setString(5, price);
			st.setString(6, "PENDING");
			st.setString(7, "0");
			st.setString(8, "");
			st.executeUpdate();
			System.out.println(1);
			return true;
		} catch (Exception e) {
			System.out.println(2);
			return false;
		}

	}

	public static boolean EditItem(String string, String name, String category, String size, String price) {

		getConnection();

		try {
			String query = "UPDATE item SET name = ?, category = ?, size = ?, price = ? WHERE Item_id = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, name);
			st.setString(2, category);
			st.setString(3, size);
			st.setString(4, price);
			st.setString(5, string);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean DeleteItem(String id) {

		getConnection();

		try {
			String query = "DELETE FROM item WHERE Item_id = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, id);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	public static ArrayList<Item> BrowseItem(String name) {
		
		getConnection();

		try {
			
			String query = "SELECT Item_id, User_id, name, category, size, price, status, offer, updatedBy FROM item WHERE status = ? AND name LIKE ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, "APPROVED");
			st.setString(2, "%" + name + "%");
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

	public static ArrayList<Item> ViewRequestedItem() {

		getConnection();

		try {
			String query = "SELECT Item_id, User_id, name, category, size, price, status, offer, updatedBy FROM item WHERE status = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, "PENDING");
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

	public static boolean OfferPrice(String userId2, String itemId, String newOffer) {

		getConnection();
		
		try {
			String query = "UPDATE item SET offer = ?, updatedBy = ? WHERE Item_id = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, newOffer);
			st.setString(2, userId2);
			st.setString(3, itemId);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public static boolean AcceptOffer(String itemId) {

		getConnection();

		try {
			String query = "UPDATE item SET status = ? WHERE Item_id = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, "SOLD");
			st.setString(2, itemId);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public static boolean DeclineOffer(String itemId) {

		getConnection();

		try {
			String query = "UPDATE item SET offer = ?, updatedBy = ? WHERE Item_id = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, "");
			st.setString(2, "");
			st.setString(3, itemId);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public static boolean ApproveItem(String id) {

		getConnection();

		try {
			String query = "UPDATE item SET status = ? WHERE Item_id = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, "APPROVED");
			st.setString(2, id);
			st.executeUpdate();
			
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean DeclineItem(String id) {

		getConnection();

		try {
			String query = "DELETE FROM item WHERE Item_id = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, id);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static ArrayList<Item> ViewAcceptedItem(String userId2) {

		getConnection();

		try {
			String query = "SELECT i.Item_id, i.User_id, i.name, i.category, i.size, i.price, i.status, i.offer, CASE WHEN w.Wishlist_id IS NULL THEN 0 ELSE 1 END AS isWishlistButtonDisabled, i.updatedBy FROM item i LEFT JOIN (SELECT * FROM wishlist WHERE User_id = ?) w ON i.Item_id = w.Item_id WHERE i.status = ?";
			
			// Query:
			//	SELECT
			// 		i.id,
			// 		i.userId,
			// 		i.name,
			// 		i.category,
			// 		i.size,
			// 		i.price,
			// 		i.status,
			// 		i.offer,
			// 		CASE
			// 			WHEN w.id IS NULL THEN 1
			// 			ELSE 0
			// 		END AS isWishlistButtonEnabled,
			// 		i.updatedBy
			// 	FROM
			// 		item i
			// 		LEFT JOIN (
			// 			SELECT * 
			// 			FROM wishlist
			// 			WHERE userId = ?
			// 		) w
			// 		ON i.id = w.itemId
			// 	WHERE
			// 		i.status = ?
			
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, userId2);
			st.setString(2, "APPROVED");
			ResultSet result = st.executeQuery();

			ArrayList<Item> itemList = new ArrayList<>();
			while (result.next()) {
				Item item = new Item(result.getString("Item_id"), result.getString("User_id"), result.getString("name"),
						result.getString("category"), result.getString("size"), result.getString("price"),
						result.getString("status"), result.getString("offer"), result.getString("updatedBy"));
				item.setIsWishlistButtonDisabled(result.getBoolean("isWishlistButtonDisabled"));
				itemList.add(item);
			}

			return itemList;

		} catch (Exception e) {
			return null;
		}

	}

	public static ArrayList<Item> ViewOfferItem(String userId2) {

		getConnection();

		try {
			String query = "SELECT Item_id, User_id, name, category, size, price, status, offer, updatedBy FROM item WHERE status = ? AND offer != ? AND User_id = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, "APPROVED");
			st.setString(2, "0");
			st.setString(3, userId2);
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
	
	public static ArrayList<Item> ViewSoldItem(String userId) {
		
		getConnection();

		try {
			String query = "SELECT Item_id, User_id, name, category, size, price, status, offer, updatedBy FROM item WHERE status = ? AND User_id = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, "SOLD");
			st.setString(2, userId);
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
	
	public static ArrayList<Item> ViewPendingItem(String userId) {
		
		getConnection();
		
		try {
			String query = "SELECT Item_id, User_id, name, category, size, price, status, offer, updatedBy FROM item WHERE status = ? AND User_id = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, "PENDING");
			st.setString(2, userId);
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
	
	public static ArrayList<Item> ViewSellerAcceptedItem(String userId2) {
		
		getConnection();
		
		try {
			String query = "SELECT Item_id, User_id, name, category, size, price, status, offer, updatedBy FROM item WHERE status = ? AND User_id = ? AND offer = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, "APPROVED");
			st.setString(2, userId2);
			st.setInt(3, 0);
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
	
	public static boolean UpdateItemInfo(String itemId) {
		
		getConnection();

		try {
			String query = "UPDATE item SET status = ?, offer = ?, updatedBy = ? WHERE Item_id = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, "SOLD");
			st.setString(2, "");
			st.setString(3, "");
			st.setString(4, itemId);
			st.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

}
