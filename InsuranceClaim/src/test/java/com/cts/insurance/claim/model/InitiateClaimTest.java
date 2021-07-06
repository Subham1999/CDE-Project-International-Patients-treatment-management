package com.cts.insurance.claim.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;


class InitiateClaimTest {
	@Test
	void testNoArgs() {
		assertThat(new InitiateClaim()).isNotNull();
	}
	@Test
	void testAllArgs() {
		assertThat(assertThat(new InitiateClaim(1,"Patient1",AilmentCategory.ORTHOPAIDICS,"insurer",1000.0))).isNotNull();
	}
	@Test
    void testTreatPlanBeanGettersAndSetters() {
        final BeanTester beanTester = new BeanTester();
        beanTester.getFactoryCollection();
        beanTester.testBean(InitiateClaim.class);
    }
}
