package com.cts.portal.exception;

@SuppressWarnings("serial")
public class IPTreatmentPackageNotFoundException extends Exception{

	/**
	 * This method sets the custom error message
	 * @param message
	 */
	public IPTreatmentPackageNotFoundException(String message) {
		
		super(message);
	}
}
