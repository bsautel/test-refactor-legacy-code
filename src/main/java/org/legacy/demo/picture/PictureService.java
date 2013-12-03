package org.legacy.demo.picture;

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

import org.legacy.demo.exception.UserNotLoggedInException;
import org.legacy.demo.user.User;

import com.google.common.base.Predicate;

public class PictureService {
	private final PictureDao pictureDao;

	public PictureService(PictureDao pictureDao) {
		this.pictureDao = pictureDao;
	}

	public List<Picture> getPicturesByUser(User user, User loggedInUser)
			throws UserNotLoggedInException {
		if (loggedInUser != null) {
			boolean isFriend = false;
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedInUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				List<Picture> userPicturesList = pictureDao
						.findPicturesByUser(user);
				return filterSharedPicture(userPicturesList);
			}
			return new ArrayList<>();
		} else {
			throw new UserNotLoggedInException();
		}
	}

	private List<Picture> filterSharedPicture(List<Picture> pictures) {
		return newArrayList(filter(pictures, new Predicate<Picture>() {
			@Override
			public boolean apply(Picture picture) {
				return picture.canBeShared();
			}
		}));
	}
}