package com.cts.iptreatment.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;


class TreatmentPlanTest {
	@Test
	void testNoArgs() {
		assertThat(new TreatmentPlan()).isNotNull();
	}
	@Test
	void testAllArgs() {
		PatientDetails patient = new PatientDetails(1, "Patient1", 20, AilmentCategory.ORTHOPAIDICS, "Package 1", LocalDate.of(2021, 03, 02));
		assertThat(assertThat(new TreatmentPlan(1, "Package 1", Arrays.asList("", ""), 1000, "specialist 1",
				LocalDate.of(2017, 03, 03), LocalDate.of(2017, 03, 03), "In-Progress", patient))).isNotNull();
	}
	@Test
    void testTreatPlanBeanGettersAndSetters() {
        final BeanTester beanTester = new BeanTester();
        beanTester.getFactoryCollection().addFactory(LocalDate.class, new LocalDateTimeFactory());
        beanTester.testBean(TreatmentPlan.class);
    }
}
