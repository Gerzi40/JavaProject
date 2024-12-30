package controller;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import model.Item;

public class ItemController {

	public static boolean UploadItem(
			String userId,
			String name,
			String category,
			String size,
			String price,
			Label nameErrorMessage,
			Label categoryErrorMessage,
			Label sizeErrorMessage,
			Label priceErrorMessage
	) {
		if(CheckItemValidation(
				name,
				category,
				size,
				price,
				nameErrorMessage,
				categoryErrorMessage,
				sizeErrorMessage,
				priceErrorMessage)
		) {
			return Item.UploadItem(userId, name, category, size, price);
		}
		
		return false;
	}

	public static boolean EditItem(
			String string,
			String name,
			String category,
			String size,
			String price,
			Label nameErrorMessage,
			Label categoryErrorMessage,
			Label sizeErrorMessage,
			Label priceErrorMessage
	) {
		if(CheckItemValidation(
				name,
				category,
				size,
				price,
				nameErrorMessage,
				categoryErrorMessage,
				sizeErrorMessage,
				priceErrorMessage)
		) {
			return Item.EditItem(string, name, category, size, price);
		}
		
		return false;
	}

	public static boolean DeleteItem(String id) {
		return Item.DeleteItem(id);
	}

	public static ObservableList<Item> BrowseItem(String name){
		ArrayList<Item> itemList = Item.BrowseItem(name);
		return FXCollections.observableArrayList(itemList);
	}
	
	private static boolean CheckItemValidation(
			String name,
			String category,
			String size,
			String price,
			Label nameErrorMessage,
			Label categoryErrorMessage,
			Label sizeErrorMessage,
			Label priceErrorMessage
	) {
		
		boolean result = true;
		
		if(name.isEmpty()) {
			nameErrorMessage.setManaged(true);
			nameErrorMessage.setText("Must be filled");
			result = false;
		} else {
			nameErrorMessage.setManaged(false);
			nameErrorMessage.setText("");
		}
		
		if(category.isEmpty()) {
			categoryErrorMessage.setManaged(true);
			categoryErrorMessage.setText("Must be filled");
			result = false;
		} else {
			categoryErrorMessage.setManaged(false);
			categoryErrorMessage.setText("");
		}
		
		if(size.isEmpty()) {
			sizeErrorMessage.setManaged(true);
			sizeErrorMessage.setText("Must be filled");
			result = false;
		} else {
			sizeErrorMessage.setManaged(false);
			sizeErrorMessage.setText("");
		}
		
		if(price.isEmpty()) {
			priceErrorMessage.setManaged(true);
			priceErrorMessage.setText("Must be filled");
			result = false;
		} else {
			priceErrorMessage.setManaged(false);
			priceErrorMessage.setText("");
		}
		
		// tidak boleh ada yang kosong
		if(result == false) return false;
		
		if(name.length() < 3) {
			nameErrorMessage.setManaged(true);
			nameErrorMessage.setText("Minimum length 3");
			result = false;
		}
		
		if(category.length() < 3) {
			categoryErrorMessage.setManaged(true);
			categoryErrorMessage.setText("Minimum length 3");
			result = false;
		}
		
		try {
			Integer priceInInt = Integer.parseInt(price);
			if(priceInInt == 0) {
				priceErrorMessage.setManaged(true);
				priceErrorMessage.setText("Price cant be zero");
				result = false;
			}
		} catch (Exception e) {
			priceErrorMessage.setManaged(true);
			priceErrorMessage.setText("Price must be a number");
			result = false;
		}
		
		return result;
		
	}

	public static ObservableList<Item> ViewRequestedItem() {
		ArrayList<Item> itemList = Item.ViewRequestedItem();
		return FXCollections.observableArrayList(itemList);
	}

	public static boolean OfferPrice(String userId, String itemId, String newOffer) {
		return Item.OfferPrice(userId, itemId, newOffer);
	}

	public static boolean AcceptOffer(String itemId) {
		return Item.AcceptOffer(itemId);
	}

	public static boolean DeclineOffer(String itemId) {
		return Item.DeclineOffer(itemId);
	}

	public static boolean ApproveItem(String id) {
		return Item.ApproveItem(id);
	}

	public static boolean DeclineItem(String id) {
		return Item.DeclineItem(id);
	}

	public static ObservableList<Item> ViewAcceptedItem(String userId) {
		ArrayList<Item> itemList = Item.ViewAcceptedItem(userId);
		return FXCollections.observableArrayList(itemList);
	}

	public static ObservableList<Item> ViewOfferItem(String userId) {
		ArrayList<Item> itemList = Item.ViewOfferItem(userId);
		return FXCollections.observableArrayList(itemList);
	}
	
	public static ObservableList<Item> ViewSoldItem(String userId) {
		ArrayList<Item> itemList = Item.ViewSoldItem(userId);
		return FXCollections.observableArrayList(itemList);
	}

	public static ObservableList<Item> ViewPendingItem(String userId) {
		ArrayList<Item> itemList = Item.ViewPendingItem(userId);
		return FXCollections.observableArrayList(itemList);
	}
	
	public static ObservableList<Item> ViewSellerAcceptedItem(String userId) {
		ArrayList<Item> itemList = Item.ViewSellerAcceptedItem(userId);
		return FXCollections.observableArrayList(itemList);
	}
	
	public static boolean UpdateItemInfo(String itemId) {
		return Item.UpdateItemInfo(itemId);
	}
	
}
