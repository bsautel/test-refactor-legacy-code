package org.legacy.demo.session;

import org.legacy.demo.exception.DependsOnCodeThatRequiresContextException;
import org.legacy.demo.user.User;

public class UserSession {
	public static User getLoggedInUser() {
		throw new DependsOnCodeThatRequiresContextException();
	}
}
