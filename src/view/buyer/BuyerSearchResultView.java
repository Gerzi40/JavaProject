package view.buyer;

import controller.ItemController;
import controller.TransactionController;
import controller.WishlistController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import model.Item;
import router.BuyerPageData;
import router.GuestPageData;
import router.Router;
import template.View;
import util.Config;
import util.Session;

public class BuyerSearchResultView extends View {

	private String itemName;
	
	private Scene scene;

	private BorderPane layout;

	private HBox navigationBox;
	private Button backButton;
	private Region spacer;
	private Button logoutButton;

	private TableView<Item> table;

	public BuyerSearchResultView(String itemName) {
		this.itemName = itemName;
		initialize();
	}
	
	public Scene getScene() {
		return scene;
	}
	
	protected void initializeHeader() {
		
		navigationBox = new HBox();
		backButton = new Button("Back");
		spacer = new Region();
		logoutButton = new Button("Logout");

		HBox.setHgrow(spacer, Priority.ALWAYS);

		navigationBox.getChildren().addAll(backButton, spacer, logoutButton);
		navigationBox.setPadding(new Insets(8));
		
	}
	
	protected void initializeBody() {
		
		table = new TableView<>();

		TableColumn<Item, String> nameColumn = new TableColumn<Item, String>("Name");
		TableColumn<Item, String> categoryColumn = new TableColumn<Item, String>("Category");
		TableColumn<Item, String> sizeColumn = new TableColumn<Item, String>("Size");
		TableColumn<Item, String> priceColumn = new TableColumn<Item, String>("Price");
		TableColumn<Item, String> offerColumn = new TableColumn<Item, String>("Offer");
		TableColumn<Item, Void> actionColumn = new TableColumn<Item, Void>("Action");

		nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("category"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("size"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
		offerColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("offer"));
		actionColumn.setCellFactory(param -> new TableCell<Item, Void>() {

			private final Button addToWishlistButton = new Button("Add To Wishlist");
			private final Button purchaseButton = new Button("Purchase");
			private final Button makeOfferButton = new Button("Make Offer");

			{
				addToWishlistButton.setOnAction(e -> {
					String itemId = getTableView().getItems().get(getIndex()).getId();
					if(WishlistController.AddWishlist(itemId)) {
						// button perlu di disable agar tidak terjadi duplicate di table wishlist
						addToWishlistButton.setDisable(true);
					}
				});
				purchaseButton.setOnAction(e -> {
					// PurchaseConfirmationPopUp.display() untuk meminta konfirmasi dari buyer
					if(PurchaseConfirmationPopUp.display()) {						
						String userId = Session.getUserId();
						String itemId = getTableView().getItems().get(getIndex()).getId();
						
						// create new transaction
						TransactionController.PurchaseItems(userId, itemId);
						
						// update item
						ItemController.UpdateItemInfo(itemId);
						
						// remove item from wishlist
						WishlistController.RemoveWishlistWithItemId(itemId);
						
						table.setItems(ItemController.ViewAcceptedItem(userId));
					}
				});
				makeOfferButton.setOnAction(e -> {
					String oldOffer = getTableView().getItems().get(getIndex()).getOffer();
					
					// OfferPricePopUp.display() untuk meminta offer buyer
					String newOffer = OfferPricePopUp.display(oldOffer);
					if(newOffer != "0") {
						String userId = Session.getUserId();
						String itemId = getTableView().getItems().get(getIndex()).getId();
						ItemController.OfferPrice(userId, itemId, newOffer);
						
						table.setItems(ItemController.ViewAcceptedItem(userId));
					}
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					HBox hBox = new HBox();
					addToWishlistButton.setDisable(getTableView().getItems().get(getIndex()).getIsWishlistButtonDisabled());
					hBox.getChildren().add(addToWishlistButton);
					hBox.getChildren().add(purchaseButton);
					hBox.getChildren().add(makeOfferButton);
					hBox.setSpacing(10);
					hBox.setAlignment(Pos.CENTER);
					setGraphic(hBox);
				}
			}
		});

		table.getColumns().add(nameColumn);
		table.getColumns().add(categoryColumn);
		table.getColumns().add(sizeColumn);
		table.getColumns().add(priceColumn);
		table.getColumns().add(offerColumn);
		table.getColumns().add(actionColumn);

		table.setItems(ItemController.BrowseItem(itemName));

	}

	protected void initializeLayout() {

		layout = new BorderPane();

		layout.setTop(navigationBox);
		layout.setCenter(table);

		scene = new Scene(layout, Config.SCENE_WIDTH, Config.SCENE_HEIGHT);

	}
	
	protected void initializeAction() {

		backButton.setOnAction(e -> navigateToBuyerHomePage());

		logoutButton.setOnAction(e -> logoutButtonClicked());

	}
	
	private void navigateToBuyerHomePage() {
		Router.getRouter().navigateTo("Home", BuyerPageData.BuyerHomePage());
	}
	
	private void logoutButtonClicked() {
		Session.setUserId("");
		Router.getRouter().navigateTo("Login", GuestPageData.LoginPage());
	}

}
