package com.cts.insurance.claim.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cts.insurance.claim.model.AilmentCategory;
import com.cts.insurance.claim.model.InitiateClaim;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class InitiateClaimRepositoryTest {
	@Autowired
	private InitiateClaimRepository repo;	
	@Test
	@Order(1)
	void testSaveInitiateClaim() {
		InitiateClaim claim = new InitiateClaim(1, "patient1", AilmentCategory.ORTHOPAIDICS, "Apollo", 1000);
		assertThat(repo.save(claim).equals(claim));
	}	
	@Test
	@Order(2)
	void testFindByName() {
		InitiateClaim claim = new InitiateClaim(1, "patient2", AilmentCategory.ORTHOPAIDICS, "Apollo", 1000);
		assertThat(repo.save(claim).equals(claim));
	}
}
