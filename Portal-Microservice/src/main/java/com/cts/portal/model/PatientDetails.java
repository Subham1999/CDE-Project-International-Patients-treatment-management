package com.cts.portal.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDetails {

	private String name;
	private int age;
	private AilmentCategory ailment;
	private String tretmentPackageName;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate treatmentCommencementDate;
}
