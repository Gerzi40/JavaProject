package view.seller;

import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DeclineReasonPopUp {

	private static String reason;

	public static String display() {

		// refresh value
		reason = "";
		
		Stage stage = new Stage();

		stage.initModality(Modality.APPLICATION_MODAL);

		GridPane layout = new GridPane();

		layout.setPadding(new Insets(16, 32, 16, 32));
		layout.setVgap(8);
		layout.setHgap(8);

		Label title = new Label("Please Provide a Reason");
		Label label = new Label("Reason");

		VBox box = new VBox();

		TextField textField = new TextField();
		Label errorMessage = new Label("Must be filled");

		Button button = new Button("Submit");

		button.setOnAction(e -> {
			reason = textField.getText();

			if (reason.isEmpty()) {
				errorMessage.setManaged(true);
			} else {
				stage.close();
			}
		});

		title.setFont(Font.font("System", FontWeight.BOLD, 16));

		box.getChildren().addAll(textField, errorMessage);

		errorMessage.setManaged(false);

		GridPane.setValignment(label, VPos.TOP);

		GridPane.setColumnSpan(title, 2);

		layout.add(title, 0, 0);
		layout.add(label, 0, 1);
		layout.add(box, 1, 1);
		layout.add(button, 1, 2);

		Scene scene = new Scene(layout, 400, 225);

		stage.setTitle("Reason for Declining");
		stage.setScene(scene);
		stage.showAndWait();

		return reason;

	}

}
