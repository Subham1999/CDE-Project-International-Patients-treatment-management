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
@ToString
@ApiModel(value = "Model object that stores the IP Treatment Package information.")
public class IPTreatmentPackage {
	
	private int treatmentPackageId;

	private AilmentCategory ailmentCategory;

	private PackageDetail packageDetail;
}
