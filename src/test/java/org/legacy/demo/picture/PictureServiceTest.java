package org.legacy.demo.picture;

import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertTrue;
import static org.legacy.demo.picture.PictureBuilder.privatePicture;
import static org.legacy.demo.picture.PictureBuilder.sharedPicture;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.legacy.demo.exception.UserNotLoggedInException;
import org.legacy.demo.user.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PictureServiceTest {
	@Mock
	private PictureDao pictureDao;
	@InjectMocks
	private PictureService pictureService;
	private User user;
	private User loggedInUser;

	@Before
	public void setUp() {
		user = new User("Bob");
		loggedInUser = new User("Alice");
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
		when(pictureDao.findPicturesByUser(user)).thenReturn(
				asList(tourEiffel, vieuxPort, montBlanc));

		List<Picture> pictures = pictureService.getPicturesByUser(user,
				loggedInUser);

		assertThat(pictures, contains(tourEiffel, montBlanc));
	}
}
