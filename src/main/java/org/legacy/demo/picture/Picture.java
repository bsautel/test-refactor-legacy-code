package org.legacy.demo.picture;

public class Picture {
	private boolean shared = true;

	public boolean canBeShared() {
		return shared;
	}

	public void disableSharing() {
		shared = false;
	}
}
