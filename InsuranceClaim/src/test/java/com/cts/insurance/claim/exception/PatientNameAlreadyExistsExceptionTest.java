package com.cts.insurance.claim.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PatientNameAlreadyExistsExceptionTest {
	private IPTreatmentPackageNotFoundException e = new IPTreatmentPackageNotFoundException("message");
	@Test
	void testMessageSetter() {
		assertThat(e).isNotNull();
	}	
}
