package com.cts.portal.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel(value = "Model object that stores the Package information.")
public class PackageDetail {

	
	private int pid;
	private String treatmentPackageName;
	private List<String> testDetails;
	private int cost;
	private int treatmentDuration;
}
