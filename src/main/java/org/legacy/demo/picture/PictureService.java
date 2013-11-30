package org.legacy.demo.picture;

import java.util.ArrayList;
import java.util.List;

import org.legacy.demo.exception.UserNotLoggedInException;
import org.legacy.demo.session.UserSession;
import org.legacy.demo.user.User;

public class PictureService {
	public List<Picture> getGamesByUser(User user)
			throws UserNotLoggedInException {
		List<Picture> picturesList = new ArrayList<Picture>();
		User loggedUser = UserSession.getLoggedInUser();
		boolean isFriend = false;
		if (loggedUser != null) {
			for (User friend : user.getFriends()) {
				if (friend.equals(loggedUser)) {
					isFriend = true;
					break;
				}
			}
			if (isFriend) {
				List<Picture> userPicturesList = PictureDao.getInstance()
						.findPicturesByUser(user);
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
}
