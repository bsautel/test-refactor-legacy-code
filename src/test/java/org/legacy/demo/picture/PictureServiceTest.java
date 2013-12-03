package org.legacy.demo.picture;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
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
	private List<Picture> userPictures;

	private class MyPictureService extends PictureService {
		@Override
		protected User getLoggedInUser() {
			return loggedInUser;
		}

		@Override
		protected List<Picture> getUserPictures(User user) {
			return userPictures;
		}
	}

	@Before
	public void setUp() {
		pictureService = new MyPictureService();
		bob = new User("Bob");
		userPictures = emptyList();
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

	@Test
	public void shouldReturnSharedPicturesWithUsersThatAreFriend()
			throws UserNotLoggedInException {
		loggedInUser = new User("Alice");
		bob.addFriend(loggedInUser);
		Picture tourEiffel = new Picture();
		Picture vieuxPort = new Picture();
		vieuxPort.disableSharing();
		Picture montBlanc = new Picture();
		userPictures = asList(tourEiffel, vieuxPort, montBlanc);

		List<Picture> pictures = pictureService.getPicturesByUser(bob);

		assertThat(pictures, contains(tourEiffel, montBlanc));
	}
}
