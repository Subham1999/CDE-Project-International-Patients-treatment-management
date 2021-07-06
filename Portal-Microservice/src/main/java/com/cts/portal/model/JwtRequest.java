package com.cts.portal.model;

import java.io.Serializable;

import lombok.Data;

@Data 
public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	private String userName;
	private String password;
	
	//need default constructor for JSON Parsing
	public JwtRequest()
	{
		
	}

	public JwtRequest(String userName, String password) {
		this.setUserName(userName);
		this.setPassword(password);
	}
}