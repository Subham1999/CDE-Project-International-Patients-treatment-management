package com.cts.insurance.claim.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;


class InsurerDetailTest {
	@Test
	void testNoArgs() {
		assertThat(new InsurerDetail()).isNotNull();
	}
	@Test
	void testAllArgs() {
		assertThat(assertThat(new InsurerDetail(1, "Apollo Munich", "Surgical Protection", 16442, 3))).isNotNull();
	}
	@Test
    void testTreatPlanBeanGettersAndSetters() {
        final BeanTester beanTester = new BeanTester();
        beanTester.getFactoryCollection();
        beanTester.testBean(InsurerDetail.class);
    }
}
