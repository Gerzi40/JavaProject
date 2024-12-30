package view.seller;

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
import router.GuestPageData;
import router.Router;
import router.SellerPageData;
import template.View;
import util.Config;
import util.Session;

public class SellerOfferedItemView extends View {

	private Scene scene;

	private BorderPane layout;

	private HBox navigationBox;
	private Button backButton;
	private Region spacer;
	private Button logoutButton;

	private TableView<Item> table;

	public SellerOfferedItemView() {
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
		navigationBox.setAlignment(Pos.CENTER);
		navigationBox.setSpacing(8);
		
	}
	
	protected void initializeBody() {
		
		table = new TableView<>();

		TableColumn<Item, String> nameColumn = new TableColumn<Item, String>("Name");
		TableColumn<Item, String> categoryColumn = new TableColumn<Item, String>("Category");
		TableColumn<Item, String> sizeColumn = new TableColumn<Item, String>("Size");
		TableColumn<Item, String> priceColumn = new TableColumn<Item, String>("Price");
		TableColumn<Item, String> offerStatusColumn = new TableColumn<Item, String>("Offer");
		TableColumn<Item, Void> actionColumn = new TableColumn<Item, Void>("Action");

		nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("category"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("size"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));
		offerStatusColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("offer"));
		actionColumn.setCellFactory(param -> new TableCell<Item, Void>() {
			private final Button acceptButton = new Button("Accept");
			private final Button declineButton = new Button("Decline");

			{
				acceptButton.setOnAction(e -> {
					String itemId = getTableView().getItems().get(getIndex()).getId();
					String buyerId = getTableView().getItems().get(getIndex()).getUpdatedBy();
					String sellerId = Session.getUserId();
					
					// create new transaction
					TransactionController.PurchaseItems(buyerId, itemId);
					
					// update item
					ItemController.AcceptOffer(itemId);
					
					// remove item from wishlist
					WishlistController.RemoveWishlistWithItemId(itemId);
					
					table.setItems(ItemController.ViewOfferItem(sellerId));
				});

				declineButton.setOnAction(e -> {
					// DeclineReasonPopUp.display() untuk meminta alasan decline kepada seller
					String reason = DeclineReasonPopUp.display();
					if(reason != null && !reason.isEmpty()) {						
						String itemId = getTableView().getItems().get(getIndex()).getId();
						if(ItemController.DeclineOffer(itemId)) {
							// refresh table
							String userId = Session.getUserId();
							table.setItems(ItemController.ViewOfferItem(userId));
						}
						
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
					hBox.getChildren().add(acceptButton);
					hBox.getChildren().add(declineButton);
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
		table.getColumns().add(offerStatusColumn);
		table.getColumns().add(actionColumn);

		String userId = Session.getUserId();
		table.setItems(ItemController.ViewOfferItem(userId));
		
	}
	
	protected void initializeLayout() {

		layout = new BorderPane();

		layout.setTop(navigationBox);
		layout.setCenter(table);

		scene = new Scene(layout, Config.SCENE_WIDTH, Config.SCENE_HEIGHT);

	}

	protected void initializeAction() {

		backButton.setOnAction(e -> navigateToSellerHomePage());

		logoutButton.setOnAction(e -> logoutButtonClicked());

	}
	
	private void navigateToSellerHomePage() {
		Router.getRouter().navigateTo("Home", SellerPageData.SellerHomePage());
	}
	
	private void logoutButtonClicked() {
		Session.setUserId("");
		Router.getRouter().navigateTo("Login", GuestPageData.LoginPage());
	}

}
