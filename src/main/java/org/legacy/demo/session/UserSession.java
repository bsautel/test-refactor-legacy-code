package org.legacy.demo.session;

import org.legacy.demo.exception.DependsOnLegacyCodeException;
import org.legacy.demo.user.User;

public class UserSession {
	public static UserSession getInstance() {
		return new UserSession();
	}

	public User getLoggedInUser() {
		throw new DependsOnLegacyCodeException();
	}
}
