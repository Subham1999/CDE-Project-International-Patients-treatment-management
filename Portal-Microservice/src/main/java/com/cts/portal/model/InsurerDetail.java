package com.cts.portal.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
@ApiModel(value = "Model object that stores the Insurer information.")
public class InsurerDetail {

	@ApiModelProperty(notes="Id of the IP Treatment Package")
	private int id;
	
	@ApiModelProperty(notes="Name of Insurer")
	private String insurerName;
	
	@ApiModelProperty(notes="Package Name of Insurance Plan")
	private String insurerPackageName;
	
	@ApiModelProperty(notes="Insurance Amount Limit")
	private double insurerPackageAmountLimit;
	
	@ApiModelProperty(notes="DIsbursement Duration")
	private int disbursementDuration;
}
