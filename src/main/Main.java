package main;

import javafx.application.Application;
import javafx.stage.Stage;
import router.GuestPageData;
import router.Router;

public class Main extends Application {

	// poin-poin penting:
	//
	// 1. kuantitas item dianggap hanya ada 1
	//		sehingga item akan hilang ketika purchase / accept offer
	//
	// 2. status item 
	//		PENDING		= pertama kali di upload oleh seller
	//		APPROVED	= item sudah di approve oleh seller
	//		SOLD		= item di purchase / accept offer
	//
	// 3. atribut offerStatus pada table item
	//		atribut offerStatus diubah menjadi offer agar lebih sesuai dengan konteks
	//
	// 4. atribut wishlist pada table item
	//		tidak akan dimasukkan pada database
	//		namun akan dimasukkan ketika memanggil ViewAcceptedItem()
	//
	// 5. atribut baru userId, offer, updatedBy, isWishlistButtonDisabled pada table item
	//		userId						= user id dari seller yang meng upload item
	//		offer						= price offer yang dibuat oleh user
	//		updatedBy					= id user yang meng offer item
	//		isWishlistButtonDisabled	= menentukan apakah 'add to wishlist' button disable atau tidak
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Router.initializeRouter(primaryStage);
		Router.getRouter().navigateTo("Register", GuestPageData.RegisterPage());
		
	}
	
}
