package com.cts.insurance.claim.exception;
@SuppressWarnings("serial")
public class PatientNameAlreadyExistsException extends Exception{

	public PatientNameAlreadyExistsException(String message) {
		
		super(message);
	}
}
