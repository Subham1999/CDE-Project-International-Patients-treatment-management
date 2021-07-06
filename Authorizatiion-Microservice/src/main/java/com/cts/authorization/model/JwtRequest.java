package com.cts.authorization.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtRequest implements Serializable {
	private static final long serialVersionUID = 5926468583005150707L;
	private String userName;
	private String password;
}