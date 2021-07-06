package com.cts.portal.exception;

@SuppressWarnings("serial")
public class PatientNameAlreadyExistsException extends Exception{

	/**
	 * This method sets the custom error message
	 * @param message
	 */
	public PatientNameAlreadyExistsException(String message) {
		
		super(message);
	}
}
