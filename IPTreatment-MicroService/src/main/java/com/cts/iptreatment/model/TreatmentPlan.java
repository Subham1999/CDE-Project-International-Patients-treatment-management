package com.cts.iptreatment.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "treatment_plan")
public class TreatmentPlan {
	@Id
	@GeneratedValue
	private int packageId;
	private String packageName;
	@ElementCollection
	private List<String> testDetails;
	private double cost;
	private String specialist;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate treatmentCommenceDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate treatmentEndDate;
	private String status;
	@ManyToOne(cascade = CascadeType.ALL)
	private PatientDetails patientDetails;
}
