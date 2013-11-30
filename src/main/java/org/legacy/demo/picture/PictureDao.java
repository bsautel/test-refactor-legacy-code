package org.legacy.demo.picture;

import java.util.List;

import org.legacy.demo.exception.DependsOnCodeThatRequiresContextException;
import org.legacy.demo.user.User;

public class PictureDao {
	public static PictureDao getInstance() {
		return new PictureDao();
	}

	public List<Picture> findPicturesByUser(User user) {
		throw new DependsOnCodeThatRequiresContextException();
	}
}
