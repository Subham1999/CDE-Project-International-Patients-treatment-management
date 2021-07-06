package com.cts.insurance.claim.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor @AllArgsConstructor @Getter @Setter
@Entity
@Table(name = "insurer_detail")
@ApiModel(value = "Model object that stores the Insurer information.")
public class InsurerDetail {
	@Id
	@GeneratedValue
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
