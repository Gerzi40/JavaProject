package view.guest;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import router.AdminPageData;
import router.BuyerPageData;
import router.GuestPageData;
import router.Router;
import router.SellerPageData;
import template.View;
import util.Config;
import util.Session;

public class LoginView extends View {
	
	private Scene scene;
	
	private GridPane layout = new GridPane();
	
	private Label usernameLabel;
	private Label passwordLabel;
	
	private TextField usernameTextField;
	private TextField passwordTextField;
	
	private Label usernameErrorMessage;
	private Label passwordErrorMessage;
	
	private VBox usernameBox;
	private VBox passwordBox;
	
	private Button loginButton;
	
	private HBox hyperlinkBox;
	private Label hyperlinkLabel;
	private Hyperlink hyperlink;
	
	public LoginView() {
		initialize();
	}
	
	public Scene getScene() {
		return scene;
	}
	
	protected void initializeBody() {
		
		usernameLabel = new Label("Username");
		passwordLabel = new Label("Password");
		
		usernameTextField = new TextField();
		passwordTextField = new TextField();
		
		usernameErrorMessage = new Label("Must be filled");
		passwordErrorMessage = new Label("Must be filled");
		
		// style="display: none"
		usernameErrorMessage.setManaged(false);
		passwordErrorMessage.setManaged(false);
		
		usernameBox = new VBox();
		passwordBox = new VBox();
		usernameBox.getChildren().addAll(usernameTextField, usernameErrorMessage);
		passwordBox.getChildren().addAll(passwordTextField, passwordErrorMessage);
		
		// align vertically at top
		GridPane.setValignment(usernameLabel, VPos.TOP);
		GridPane.setValignment(passwordLabel, VPos.TOP);
		
		loginButton = new Button("Login");
		
		initializeHyperlink();
		
	}
	
	private void initializeHyperlink() {
		
		hyperlinkBox = new HBox();
		hyperlinkLabel = new Label("Dont have an account? ");
		hyperlink = new Hyperlink("Register");
		hyperlink.setStyle("-fx-padding: 0;");
		hyperlinkBox.getChildren().addAll(hyperlinkLabel, hyperlink);
		
	}
	
	protected void initializeLayout() {
		
		layout = new GridPane();
		
		layout.setPadding(new Insets(16, 16, 16, 16));
		layout.setVgap(8);
		layout.setHgap(8);
		
		layout.add(usernameLabel, 0, 0);
		layout.add(passwordLabel, 0, 1);
		
		layout.add(usernameBox, 1, 0);
		layout.add(passwordBox, 1, 1);
		
		layout.add(loginButton, 1, 2);
		
		layout.add(hyperlinkBox, 1, 3);
		
		scene = new Scene(layout, Config.SCENE_WIDTH, Config.SCENE_HEIGHT);
		
	}
	
	protected void initializeAction() {
		
		loginButton.setOnAction(e -> {
			
			String username = usernameTextField.getText();
			String password = passwordTextField.getText();
			
			if(UserController.Login(
					username,
					password,
					usernameErrorMessage,
					passwordErrorMessage)
			) {
				if(Session.getUserRole().equals("admin")) {
					Router.getRouter().navigateTo("Home", AdminPageData.AdminHomePage());
				} else if(Session.getUserRole().equals("Buyer")) {
					Router.getRouter().navigateTo("Home", BuyerPageData.BuyerHomePage());
				} else if(Session.getUserRole().equals("Seller")) {
					Router.getRouter().navigateTo("Home", SellerPageData.SellerHomePage());
				}
			}
			
		});
		
		hyperlink.setOnAction(e -> navigateToRegisterPage());
		
	}
	
	private void navigateToRegisterPage() {
		
		Router.getRouter().navigateTo("Register", GuestPageData.RegisterPage());
		
	}
	
}
