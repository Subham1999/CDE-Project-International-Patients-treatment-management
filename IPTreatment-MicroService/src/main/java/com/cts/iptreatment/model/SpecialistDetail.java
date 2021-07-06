package com.cts.iptreatment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpecialistDetail {
	private int specialistId;
	private String name;
	private AilmentCategory areaOfExpertise;
	private int experienceInYears;
	private long contactNumber;

}
