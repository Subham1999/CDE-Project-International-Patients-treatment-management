package com.cts.mfpe.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "Model object that stores the Specialist details.")
@Entity
@Table(name = "specialists")
public class SpecialistDetail {
	
	@ApiModelProperty(notes="Id of the Specialist")
    @Id
	private int specialistId;
	
	@NotNull(message = "Name is mandatory")
	@ApiModelProperty(notes="Name of the Specialist")
	private String name;
	
	@NotNull(message = "Expertise is mandatory")
	@ApiModelProperty(notes="Area of expertise of the Specialist")
	private AilmentCategory areaOfExpertise;
	
	@NotNull(message = "Experience is mandatory")
	@Min(value = 1, message = "Minimum year of Experience is 1")
	@ApiModelProperty(notes="Years of experience of the Specialist")
	private int experienceInYears;

	@NotNull(message = "Contact number is mandatory")
	@ApiModelProperty(notes="Contact number of the Specialist")
	private long contactNumber;
	
	
}
