package util;

public class Session {

	private static String User_id;
	private static String userRole;

	public static String getUserId() {
		return User_id;
	}

	public static void setUserId(String i) {
		Session.User_id = i;
	}

	public static String getUserRole() {
		return userRole;
	}

	public static void setUserRole(String userRole) {
		Session.userRole = userRole;
	}
	
}
