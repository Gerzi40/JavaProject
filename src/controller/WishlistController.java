package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.Wishlist;
import util.Session;

public class WishlistController {

	public static ObservableList<Item> ViewWishlist() {
		String userId = Session.getUserId();
		ArrayList<Item> itemList = Wishlist.ViewWishlist(userId);
		return FXCollections.observableArrayList(itemList);
	}
	
	public static boolean AddWishlist(String itemId) {
		String userId = Session.getUserId();
		return Wishlist.AddWishlist(itemId, userId);
	}
	
	public static boolean RemoveWishlistWithItemIdUserId(String itemId) {
		String userId = Session.getUserId();
		return Wishlist.RemoveWishlist(itemId, userId);
	}
	
	public static boolean RemoveWishlistWithItemId(String itemId) {
		return Wishlist.RemoveWishlist(itemId);
	}
	
}
