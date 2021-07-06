package com.cts.iptreatment.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;


class SpecialistDetailsTest {
	@Test
	void testNoArgs() {
		assertThat(new SpecialistDetail()).isNotNull();
	}
	@Test
	void testAllArgs() {
		SpecialistDetail specialistDetail = new SpecialistDetail(1, "Specialist", AilmentCategory.ORTHOPAIDICS, 4, 1122223334);
		assertThat(assertThat(specialistDetail).isNotNull());
	}
	@Test
    void testPatientDetailsGettersAndSetters() {
        final BeanTester beanTester = new BeanTester();
        beanTester.getFactoryCollection();
        beanTester.testBean(SpecialistDetail.class);
    }
}
