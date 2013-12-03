package org.legacy.demo.picture;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.legacy.demo.exception.UserNotLoggedInException;
import org.legacy.demo.user.User;

public class PictureServiceTest {
	private PictureService pictureService;
	private User bob;
	private User loggedInUser;

	private class MyPictureService extends PictureService {
		@Override
		protected User getLoggedInUser() {
			return loggedInUser;
		}
	}

	@Before
	public void setUp() {
		pictureService = new MyPictureService();
		bob = new User("Bob");
	}

	@Test(expected = UserNotLoggedInException.class)
	public void shouldThrowAnExceptionWhenUserIsNotLoggedIn()
			throws UserNotLoggedInException {
		loggedInUser = null;

		pictureService.getPicturesByUser(bob);
	}

	@Test
	public void shoudReturnNoPictureWithTwoUsersThatAreNotFriend()
			throws UserNotLoggedInException {
		loggedInUser = new User("Alice");

		List<Picture> pictures = pictureService.getPicturesByUser(bob);

		assertTrue(pictures.isEmpty());
	}
}
