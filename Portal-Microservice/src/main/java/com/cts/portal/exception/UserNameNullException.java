package com.cts.portal.exception;

@SuppressWarnings("serial")
public class UserNameNullException extends RuntimeException {

	/**
	 * This method sets the custom error message
	 * @param message
	 */
	public UserNameNullException(String message) {
		super(message);
	}

}
