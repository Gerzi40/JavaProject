package view.seller;

import controller.ItemController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class SellerPendingItemView extends View {
	
	private Scene scene;

	private BorderPane layout;
	
	private HBox navigationBox;
	private Button backButton;
	private Region spacer;
	private Button logoutButton;
	
	private TableView<Item> table;
	
	public SellerPendingItemView() {
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
		
		nameColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
		categoryColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("category"));
		sizeColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("size"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Item, String>("price"));			
		
		table.getColumns().add(nameColumn);
		table.getColumns().add(categoryColumn);
		table.getColumns().add(sizeColumn);
		table.getColumns().add(priceColumn);
		
		String userId = Session.getUserId();
		table.setItems(ItemController.ViewPendingItem(userId));
		
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
