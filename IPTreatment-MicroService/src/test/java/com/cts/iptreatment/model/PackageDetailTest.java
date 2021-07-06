package com.cts.iptreatment.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.meanbean.test.BeanTester;


class PackageDetailTest {
	@Test
	void testNoArgs() {
		assertThat(new PackageDetail()).isNotNull();
	}
	@Test
	void testAllArgs() {
		PackageDetail packageDetail = new PackageDetail(1, "package 1", Arrays.asList("",""),12000,3);
		assertThat(assertThat(packageDetail).isNotNull());
	}
	@Test
    void testTreatPlanBeanGettersAndSetters() {
        final BeanTester beanTester = new BeanTester();
        beanTester.getFactoryCollection().addFactory(LocalDate.class, new LocalDateTimeFactory());
        beanTester.testBean(PackageDetail.class);
    }
}
