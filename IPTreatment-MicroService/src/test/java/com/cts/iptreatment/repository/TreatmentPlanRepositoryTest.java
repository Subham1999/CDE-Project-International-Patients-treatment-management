package com.cts.iptreatment.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cts.iptreatment.model.TreatmentPlan;

@DataJpaTest
class TreatmentPlanRepositoryTest {
	
	@Autowired
	private TreatmentPlanRepository treatmentPlanRepo;

	@Test
	public void testSaveTreatmentPlan() {
		TreatmentPlan plan = new TreatmentPlan();
		assertThat(treatmentPlanRepo.save(plan).equals(plan));
	}
	
	@Test
	public void testGetTreatmentPlan() {
		assertThat(treatmentPlanRepo.findAll().isEmpty());
	}
}
