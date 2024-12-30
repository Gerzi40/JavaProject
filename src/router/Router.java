package router;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class Router {

	private static Router router;

	private Stage primaryStage;
	
	private Router(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public static Router initializeRouter(Stage primaryStage) {
		if(router == null) {
			router = new Router(primaryStage);
		}
		return router;
	}
	
	public static Router getRouter() {
		if(router == null) {
			System.out.println("Router is null");
		}
		return router;
	}
	
	public void navigateTo(String title, Scene scene) {
		primaryStage.setTitle(title);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
}
