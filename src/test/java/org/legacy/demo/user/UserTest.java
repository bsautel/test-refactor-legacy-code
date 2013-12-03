package org.legacy.demo.user;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UserTest {
	private User bob;
	private User alice;

	@Before
	public void setUp() {
		bob = new User("Bob");
		alice = new User("Alice");

	}

	@Test
	public void shouldTellThatUsersAreNotFriend() {
		bob.addFriend(new User("John"));

		assertFalse(bob.isFriendWith(alice));
	}

	@Test
	public void shouldTellThatUsersAreFriend() {
		bob.addFriend(alice);

		assertTrue(bob.isFriendWith(alice));
	}
}
