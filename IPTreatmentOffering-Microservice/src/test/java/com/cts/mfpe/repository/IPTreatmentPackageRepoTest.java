package com.cts.mfpe.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cts.mfpe.model.AilmentCategory;

@DataJpaTest(showSql = true)
class IPTreatmentPackageRepoTest {

	@Autowired
	IPTreatmentPackageRepository treatmentPackageRepository;
	
	@Autowired
	PackageDetailRepository packageDetailRepository;
	
	@Test
	@DisplayName("Test findAll() of IPTreatmentPackageRepo")
	public void testfindAll() {
	
		assertThat(treatmentPackageRepository.findAll()).hasSize(4);
	}
	
	@Test
	@DisplayName("Test findByName(ailment, packageName) of IPTreatmentPackageRepo")
	public void testfindByName() {
	
		assertNotNull(treatmentPackageRepository.findByName(AilmentCategory.ORTHOPAIDICS, "Package 1"));
	}
	
	@Test
	@DisplayName("Test invalid findByName(ailment, packageName) of IPTreatmentPackageRepo")
	public void testInvalidfindByName() {
	
		assertThat(treatmentPackageRepository.findByName(AilmentCategory.ORTHOPAIDICS, "Package 3")).isEmpty();
	}
	

}
