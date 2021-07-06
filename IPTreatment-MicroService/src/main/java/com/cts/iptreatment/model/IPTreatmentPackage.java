package com.cts.iptreatment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IPTreatmentPackage {
	private int treatmentPackageId;
	private AilmentCategory ailmentCategory;
	private PackageDetail packageDetail;
}
