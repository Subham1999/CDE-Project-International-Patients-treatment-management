package com.cts.portal.exception;

@SuppressWarnings("serial")
public class UserNameAlreadyExistException extends Exception{

	/**
	 * This method sets the custom error message
	 * @param message
	 */
	public UserNameAlreadyExistException(String message) {
		
		super(message);
	}
}
