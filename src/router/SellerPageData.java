package router;

import javafx.scene.Scene;
import model.Item;
import view.seller.SellerEditItemView;
import view.seller.SellerHomeView;
import view.seller.SellerOfferedItemView;
import view.seller.SellerPendingItemView;
import view.seller.SellerSoldItemView;
import view.seller.SellerUploadItemView;

public class SellerPageData {

	public static Scene SellerEditItemPage(Item item) {
		return new SellerEditItemView(item).getScene();
	}
	public static Scene SellerHomePage() {
		return new SellerHomeView().getScene();
	}
	public static Scene SellerOfferedItemPage() {
		return new SellerOfferedItemView().getScene();
	}
	public static Scene SellerPendingItemPage() {
		return new SellerPendingItemView().getScene();
	}
	public static Scene SellerSoldItemPage() {
		return new SellerSoldItemView().getScene();
	}
	public static Scene SellerUploadItemPage() {
		return new SellerUploadItemView().getScene();
	}
	
}
