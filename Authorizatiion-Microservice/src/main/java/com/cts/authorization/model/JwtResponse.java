package com.cts.authorization.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class JwtResponse implements Serializable {
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwtToken;
	private List<String> roles;

	public JwtResponse(String jwtToken) {
		super();
		this.jwtToken = jwtToken;
	}

}