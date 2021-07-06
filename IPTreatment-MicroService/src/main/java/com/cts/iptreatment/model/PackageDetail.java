package com.cts.iptreatment.model;

import java.util.List;

import javax.persistence.ElementCollection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageDetail {
	private int pid;
	private String treatmentPackageName;
	@ElementCollection
	private List<String> testDetails;
	private int cost;
	private int treatmentDuration;
}
