package com.cts.iptreatment.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cts.iptreatment.model.PatientDetails;

@DataJpaTest
class PatientDetailsRepositoryTest {

	@Autowired
	private PatientDetailsRepository patientDetailsRepository;
	
	@Test
	public void testSavePatientDetails() {
		PatientDetails patientDetails = new PatientDetails();
		assertThat(patientDetailsRepository.save(patientDetails).equals(patientDetails));
	}
	
	@Test
	public void testGetPatientDetails() {
		assertThat(patientDetailsRepository.findAll().isEmpty());
	}
	

}
