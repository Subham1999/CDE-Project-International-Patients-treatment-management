package com.cts.insurance.claim.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


class PatientDetailsTest {
	private PatientDetails patient = new PatientDetails(1, "Patient1", 20, AilmentCategory.ORTHOPAIDICS, "Package 1", LocalDate.of(2021, 03, 02));	
	@Test
	void testSetterId() {
		patient.setPatientId(1);
		assertThat(Integer.valueOf(patient.getPatientId()).equals(Integer.valueOf(1)));
	}
	@Test
	void testSetterPatientName() {
		patient.setName("Patient1");
		assertThat(patient.getName().equals("Patient1"));
	}
	@Test
	void testSetterAge() {
		patient.setAge(30);
		assertThat(Integer.valueOf(patient.getAge()).equals(30));
	}
	@Test
	void testSetterAilment() {
		patient.setAilment(AilmentCategory.ORTHOPAIDICS);
		assertThat(patient.getAilment().equals(AilmentCategory.ORTHOPAIDICS));
	}
	@Test
	void testSetterPackage() {
		patient.setTretmentPackageName("Package 1");
		assertThat(patient.getTretmentPackageName().equals("Package 1"));
	}
	@Test
	void testSetterTime() {
		patient.setTreatmentCommencementDate(LocalDate.of(2021, 03, 03));
		assertThat(patient.getTreatmentCommencementDate().equals(LocalDate.of(2021, 03, 03)));
	}
	@Test
	void testNoArg() {
		PatientDetails patient1 = new PatientDetails();
		patient1.setTretmentPackageName("Package 1");
		assertThat(patient.getTretmentPackageName().equals("Package 1"));
	}
}
