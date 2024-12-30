package view.guest;

import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import router.GuestPageData;
import router.Router;
import template.View;
import util.Config;

public class RegisterView extends View {

	
	private Scene scene;
	
	private GridPane layout;
	
	private Label usernameLabel;
	private Label passwordLabel;
	private Label phoneNumberLabel;
	private Label addressLabel;
	private Label roleLabel;
	
	private TextField usernameTextField;
	private TextField passwordTextField;
	private TextField phoneNumberTextField;
	private TextField addressTextField;
	
	private ToggleGroup roleToggleGroup;
	private RadioButton buyerOption;
	private RadioButton sellerOption;
	private HBox roleOptionBox;
	
	private Label usernameErrorMessage;
	private Label passwordErrorMessage;
	private Label phoneNumberErrorMessage;
	private Label addressErrorMessage;
	private Label roleErrorMessage;
	
	private VBox usernameBox;
	private VBox passwordBox;
	private VBox phoneNumberBox;
	private VBox addressBox;
	private VBox roleBox;
	
	private Button registerButton;
	
	private HBox hyperlinkBox;
	private Label hyperlinkLabel;
	private Hyperlink hyperlink;
	
	public RegisterView() {
		initialize();
	}
	
	public Scene getScene() {
		return scene;
	}
	
	protected void initializeBody() {
		
		usernameLabel = new Label("Username");
		passwordLabel = new Label("Password");
		phoneNumberLabel = new Label("Phone Number");
		addressLabel = new Label("Address");
		roleLabel = new Label("Role");
		
		usernameTextField = new TextField();
		passwordTextField = new TextField();
		phoneNumberTextField = new TextField();
		addressTextField = new TextField();
		
		roleToggleGroup = new ToggleGroup();
		buyerOption = new RadioButton("Buyer");
		sellerOption = new RadioButton("Seller");
		buyerOption.setToggleGroup(roleToggleGroup);
		sellerOption.setToggleGroup(roleToggleGroup);
		roleOptionBox = new HBox();
		roleOptionBox.getChildren().addAll(buyerOption, sellerOption);
		roleOptionBox.setSpacing(8);
		
		usernameErrorMessage = new Label();
		passwordErrorMessage = new Label();
		phoneNumberErrorMessage = new Label();
		addressErrorMessage = new Label();
		roleErrorMessage = new Label();
		
		// style="display: none"
		usernameErrorMessage.setManaged(false);
		passwordErrorMessage.setManaged(false);
		phoneNumberErrorMessage.setManaged(false);
		addressErrorMessage.setManaged(false);
		roleErrorMessage.setManaged(false);
		
		usernameBox = new VBox();
		passwordBox = new VBox();
		phoneNumberBox = new VBox();
		addressBox = new VBox();
		roleBox = new VBox();
		
		usernameBox.getChildren().addAll(usernameTextField, usernameErrorMessage);
		passwordBox.getChildren().addAll(passwordTextField, passwordErrorMessage);
		phoneNumberBox.getChildren().addAll(phoneNumberTextField, phoneNumberErrorMessage);
		addressBox.getChildren().addAll(addressTextField, addressErrorMessage);
		roleBox.getChildren().addAll(roleOptionBox, roleErrorMessage);
		
		// align vertically at top
		GridPane.setValignment(usernameLabel, VPos.TOP);
		GridPane.setValignment(passwordLabel, VPos.TOP);
		GridPane.setValignment(phoneNumberLabel, VPos.TOP);
		GridPane.setValignment(addressLabel, VPos.TOP);
		GridPane.setValignment(roleLabel, VPos.TOP);
		
		registerButton = new Button("Register");
		
		initializeHyperlink();
		
	}
	
	private void initializeHyperlink() {
		
		hyperlinkBox = new HBox();
		hyperlinkLabel = new Label("Already have an account? ");
		hyperlink = new Hyperlink("Login");
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
		layout.add(phoneNumberLabel, 0, 2);
		layout.add(addressLabel, 0, 3);
		layout.add(roleLabel, 0, 4);
		
		layout.add(usernameBox, 1, 0);
		layout.add(passwordBox, 1, 1);
		layout.add(phoneNumberBox, 1, 2);
		layout.add(addressBox, 1, 3);
		layout.add(roleBox, 1, 4);
		
		layout.add(registerButton, 1, 5);
		
		layout.add(hyperlinkBox, 1, 6);
		
		scene = new Scene(layout, Config.SCENE_WIDTH, Config.SCENE_HEIGHT);
		
	}
	
	protected void initializeAction() {
		
		registerButton.setOnAction(e -> registerButtonClicked());
		
		hyperlink.setOnAction(e -> navigateToLoginPage());
		
	}
	
	private void registerButtonClicked() {
		
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();
		String phoneNumber = phoneNumberTextField.getText();
		String address = addressTextField.getText();
		String role = "";
		
		RadioButton roleRadioButton = (RadioButton) roleToggleGroup.getSelectedToggle();
		if(roleRadioButton != null) {			
			role = roleRadioButton.getText();
		}
		
		if(UserController.Register(
				username,
				password,
				phoneNumber,
				address,
				role,
				usernameErrorMessage,
				passwordErrorMessage,
				phoneNumberErrorMessage,
				addressErrorMessage,
				roleErrorMessage)
		) {
			navigateToLoginPage();
		}
		
	}
	
	private void navigateToLoginPage() {
		Router.getRouter().navigateTo("Login", GuestPageData.LoginPage());
	}
	
}
