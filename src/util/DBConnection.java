package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ooad"; 
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    private static DBConnection instance;
    private Connection connection;
    private DBConnection() {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    }
    public static DBConnection getInstance() {
    	if(instance==null) {
    		instance = new DBConnection();
    	}
    	return instance;
    }
    public Connection getConnnection() {
    	return connection;
    }
	
}
