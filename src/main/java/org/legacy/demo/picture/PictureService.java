package org.legacy.demo.picture;

import java.util.ArrayList;
import java.util.List;

import org.legacy.demo.exception.UserNotLoggedInException;
import org.legacy.demo.session.UserSession;
import org.legacy.demo.user.User;

public class PictureService {
	public List<Picture> getPicturesByUser(User user)
			throws UserNotLoggedInException {
		List<Picture> picturesList = new ArrayList<Picture>();
		User loggedInUser = getLoggedInUser();
		boolean isFriend = false;
		if (loggedInUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedInUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				List<Picture> userPicturesList = getUserPictures(user);
				for (Picture picture : userPicturesList) {
					if (picture.canBeShared()) {
						picturesList.add(picture);
					}
				}
			}
			return picturesList;
		} else {
			throw new UserNotLoggedInException();
		}
	}

	protected List<Picture> getUserPictures(User user) {
		return PictureDao.getInstance().findPicturesByUser(user);
	}

	protected User getLoggedInUser() {
		return UserSession.getLoggedInUser();
	}
}
