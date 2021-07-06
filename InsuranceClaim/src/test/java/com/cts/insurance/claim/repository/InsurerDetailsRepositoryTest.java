package com.cts.insurance.claim.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cts.insurance.claim.model.InsurerDetail;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class InsurerDetailsRepositoryTest {
	@Autowired
	private InsurerDetailRepository repo;
	@Test
	@Order(1)
	void testFindAll() {
		assertThat(repo.findAll().isEmpty());
	}
	@Test
	@Order(2)
	void testSave() {
		InsurerDetail insurer = new InsurerDetail(1, "Apollo", "HealthPack", 14000, 4);
		assertThat(repo.save(insurer).equals(insurer));
	}
	@Test
	@Order(3)
	void testFindByName() {
		InsurerDetail insurer = new InsurerDetail(1, "Apollo", "HealthPack", 14000, 4);
		repo.save(insurer).equals(insurer);
		assertThat(repo.findByName("Apollo").equals(insurer));
	}
}
