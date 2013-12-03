package org.legacy.demo.picture;

import org.junit.Test;
import org.legacy.demo.exception.UserNotLoggedInException;
import org.legacy.demo.user.User;

public class PictureServiceTest {
	private class MyPictureService extends PictureService {
		@Override
		protected User getLoggedInUser() {
			return null;
		}
	}

	@Test(expected = UserNotLoggedInException.class)
	public void shouldThrowAnExceptionWhenUserIsNotLoggedIn()
			throws UserNotLoggedInException {
		PictureService pictureService = new MyPictureService();
		User bob = new User("Bob");

		pictureService.getPicturesByUser(bob);
	}
}
