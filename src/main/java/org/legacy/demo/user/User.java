package org.legacy.demo.user;

import java.util.LinkedList;
import java.util.List;

public class User {
	private final List<User> friends = new LinkedList<>();
	private final String name;

	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<User> getFriends() {
		return friends;
	}

	public void addFriend(User friend) {
		friends.add(friend);
	}
}
