module final_project {
	opens main;
	opens controller;
	opens view.admin;
	opens view.buyer;
	opens view.guest;
	opens view.seller;
	opens model;
	requires javafx.graphics;
	requires javafx.controls;
	requires java.sql;
}