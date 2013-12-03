package org.legacy.demo.picture;

public class PictureBuilder {
	public static Picture sharedPicture() {
		return new Picture();
	}

	public static Picture privatePicture() {
		Picture picture = sharedPicture();
		picture.disableSharing();
		return picture;
	}
}
