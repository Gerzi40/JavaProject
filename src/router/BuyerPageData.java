package router;

import javafx.scene.Scene;
import view.buyer.BuyerHomeView;
import view.buyer.BuyerPurchaseHistoryView;
import view.buyer.BuyerSearchResultView;
import view.buyer.BuyerWishlistView;

public class BuyerPageData {

	public static Scene BuyerHomePage() {
		return new BuyerHomeView().getScene();
	}
	
	public static Scene BuyerPurchaseHistoryPage() {
		return new BuyerPurchaseHistoryView().getScene();
	}
	
	public static Scene BuyerWishlistPage() {
		return new BuyerWishlistView().getScene();
	}
	
	public static Scene BuyerSearchResultPage(String itemName) {
		return new BuyerSearchResultView(itemName).getScene();
	}
	
}
