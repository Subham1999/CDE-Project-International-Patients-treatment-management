package com.cts.portal.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class InitiateClaim {

	private int id;
	
	private String patientName;
	
	private AilmentCategory ailment;
	
	private String insurerName;
	
}
