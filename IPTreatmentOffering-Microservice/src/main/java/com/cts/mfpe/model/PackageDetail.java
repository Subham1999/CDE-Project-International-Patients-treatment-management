package com.cts.mfpe.model;

import java.util.List;

import javax.persistence.ElementCollection;
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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value = "Model object that stores the Package information.")
@Entity
@Table(name = "packages")
public class PackageDetail {

	@ApiModelProperty(notes = "id of the package")
	@Id
	private int pid;
	
	@NotNull(message = "Package name is mandatory")
	@ApiModelProperty(notes = "Name of the package")
	private String treatmentPackageName;
	
	@NotNull(message = "Tests are mandatory")
	@ApiModelProperty(notes = "Medical tests in the package")
	@ElementCollection(targetClass=String.class)
	private List<String> testDetails;
	
	@NotNull(message = "Cost is mandatory")
	@Min(value = 500, message = "Minimum cost is 500")
	@ApiModelProperty(notes = "Cost of the package")
	private int cost;
	
	@NotNull(message = "Duration is mandatory")
	@Min(value = 1, message = "Minimum duration is 1")
	@ApiModelProperty(notes = "Duration of the package")
	private int treatmentDuration;
}
