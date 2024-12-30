package view.buyer;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PurchaseConfirmationPopUp {

	private static boolean answer;
	
	public static boolean display() {
		
		// refresh value
		answer = false;
		
		Stage stage = new Stage();
		
		stage.initModality(Modality.APPLICATION_MODAL);
		
		VBox layout = new VBox();
		
		Label title = new Label("Are you sure you want to purchase this item?");
		
		HBox box = new HBox();
		Button purchaseButton = new Button("Purchase");
		Button cancelButton = new Button("Cancel");
		
		purchaseButton.setOnAction(e -> {
			answer = true;
			stage.close();
		});
		cancelButton.setOnAction(e -> {
			answer = false;
			stage.close();
		});
		
		box.getChildren().addAll(purchaseButton, cancelButton);
		box.setAlignment(Pos.CENTER);
		box.setSpacing(32);
		
		title.setFont(Font.font("System", FontWeight.BOLD, 16));
		
		layout.getChildren().addAll(title, box);
		
		layout.setAlignment(Pos.CENTER);
		layout.setSpacing(24);
		
		Scene scene = new Scene(layout, 400, 150);
		
		stage.setTitle("Purchase Confirmation");
		stage.setScene(scene);
		stage.showAndWait();
		
		return answer;
		
	}
	
}
