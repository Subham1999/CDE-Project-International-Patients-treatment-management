package com.cts.mfpe.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
@ApiModel(value = "Model object that stores the IP Treatment Package information.")
@Entity
@Table(name = "iptreatmentpackages")
public class IPTreatmentPackage {
	
	@ApiModelProperty(notes="Id of the IP Treatment Package")
    @Id @GeneratedValue
	private int treatmentPackageId;

	@NotNull(message = "Ailment category is mandatory")
	@ApiModelProperty(notes = "Ailment category covered in the IP Treatment Package")
	private AilmentCategory ailmentCategory;

	@ApiModelProperty(notes = "IP Treatment Package details")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="package", unique=true)
	private PackageDetail packageDetail;
}
