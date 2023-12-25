package authentication;

import java.util.*;

import core.AppState;
import model.*;

public final class AuthenticationService {
	public User authenticate(String email, String password) {
		Vector<User> users = AppState.getInstance().getUsers();

		for (User user : users) {
			if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
				Session.setCurrentUser(user);

				return user;
			}
		}

		return null;
	}
}
