package com.cts.insurance.claim.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "initiate_claim")
public class InitiateClaim {
	@Id
	@GeneratedValue
	private int id;	
	@ApiModelProperty(notes="Patient Name")
	private String patientName;	
	@ApiModelProperty(notes="Ailment")
	private AilmentCategory ailment;
	@ApiModelProperty(notes="Insurer Name")
	private String insurerName;
	@ApiModelProperty(notes="Balance Amount To Be Paid")
	private double balanceAmtToBePaid;
}
