package authentication;

import model.User;

public final class Session {
	private static User currentUser;
	
	public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }
    
    public static void removeCurrentUser() {
        currentUser = null;
    }
}
