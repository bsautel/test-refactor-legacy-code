package org.legacy.demo.exception;

public class DependsOnLegacyCodeException extends RuntimeException {
	private static final long serialVersionUID = 5115564917026532566L;

	public DependsOnLegacyCodeException() {
		super("Unable to run this code inside a JUnit test");
	}
}
