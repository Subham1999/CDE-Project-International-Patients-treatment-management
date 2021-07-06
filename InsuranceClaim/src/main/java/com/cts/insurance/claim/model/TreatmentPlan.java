package com.cts.insurance.claim.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentPlan {
	private int packageId;
	private String packageName;
	private List<String> testDetails;
	private double cost;
	private String specialist;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate treatmentCommenceDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate treatmentEndDate;
	private String status;
	private PatientDetails patientDetails;
}
