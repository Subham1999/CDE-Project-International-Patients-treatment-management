package com.cts.portal.model;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Model object that stores the Specialist details.")
@ToString
public class SpecialistDetail {

	private int specialistId;
	private String name;
	private String areaOfExpertise;
	private int experienceInYears;
	private long contactNumber;

}
