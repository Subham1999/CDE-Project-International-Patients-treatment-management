package com.cts.iptreatment.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;


class PatientDetailsTest {
	@Test
	void testNoArgs() {
		assertThat(new PatientDetails()).isNotNull();
	}
	@Test
	void testAllArgs() {
		PatientDetails patient = new PatientDetails(1, "Patient1", 20, AilmentCategory.ORTHOPAIDICS, "Package 1", LocalDate.of(2021, 03, 02));
		assertThat(assertThat(patient).isNotNull());
	}
	@Test
    void testPatientDetailsGettersAndSetters() {
        final BeanTester beanTester = new BeanTester();
        beanTester.getFactoryCollection().addFactory(LocalDate.class, new LocalDateTimeFactory());
        beanTester.testBean(PatientDetails.class);
    }
}
