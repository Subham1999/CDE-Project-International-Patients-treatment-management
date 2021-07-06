package com.cts.iptreatment.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
@Table(name = "patient_details")
public class PatientDetails {
	@Id
	@GeneratedValue
	private int patientId;
	private String name;
	private int age;
	private AilmentCategory ailment;
	private String tretmentPackageName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate TreatmentCommencementDate;

}
