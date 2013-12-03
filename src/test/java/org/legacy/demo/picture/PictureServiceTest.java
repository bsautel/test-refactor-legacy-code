package org.legacy.demo.picture;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertTrue;
import static org.legacy.demo.picture.PictureBuilder.privatePicture;
import static org.legacy.demo.picture.PictureBuilder.sharedPicture;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.legacy.demo.exception.UserNotLoggedInException;
import org.legacy.demo.user.User;

public class PictureServiceTest {
	private PictureService pictureService;
	private User user;
	private User loggedInUser;
	private List<Picture> userPictures;

	private class MyPictureService extends PictureService {
		@Override
		protected List<Picture> getUserPictures(User user) {
			return userPictures;
		}
	}

	@Before
	public void setUp() {
		pictureService = new MyPictureService();
		user = new User("Bob");
		loggedInUser = new User("Alice");
		userPictures = emptyList();
	}

	@Test(expected = UserNotLoggedInException.class)
	public void shouldThrowAnExceptionWhenUserIsNotLoggedIn()
			throws UserNotLoggedInException {
		loggedInUser = null;

		pictureService.getPicturesByUser(user, null);
	}

	@Test
	public void shoudReturnNoPictureWithTwoUsersThatAreNotFriend()
			throws UserNotLoggedInException {
		user.addFriend(new User("John"));

		List<Picture> pictures = pictureService.getPicturesByUser(user,
				loggedInUser);

		assertTrue(pictures.isEmpty());
	}

	@Test
	public void shouldReturnSharedPicturesWithUsersThatAreFriend()
			throws UserNotLoggedInException {
		user.addFriend(loggedInUser);
		Picture tourEiffel = sharedPicture();
		Picture vieuxPort = privatePicture();
		Picture montBlanc = sharedPicture();
		userPictures = asList(tourEiffel, vieuxPort, montBlanc);

		List<Picture> pictures = pictureService.getPicturesByUser(user,
				loggedInUser);

		assertThat(pictures, contains(tourEiffel, montBlanc));
	}
}
