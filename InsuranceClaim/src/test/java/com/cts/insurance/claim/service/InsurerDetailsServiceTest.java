package com.cts.insurance.claim.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.insurance.claim.model.InsurerDetail;
import com.cts.insurance.claim.repository.InsurerDetailRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class InsurerDetailsServiceTest {
	@Mock
	private InsurerDetailRepository repo;
	@InjectMocks
	private AuditSeverityServiceImpl service;
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	@Order(1)
	public void testGetAllInsurers() throws Exception {
		assertThat(service.getAllInsurers().isEmpty());
	}
	@Test
	@Order(2)
	public void testAddInsurers() {
		InsurerDetail insurer = new InsurerDetail(1, "Apollo", "HealthPack", 14000, 4);
		when(repo.save(insurer)).thenReturn(insurer);
		assertThat(service.addInsurers(insurer).equals(insurer));
	}
	@Test
	@Order(3)
	public void testGetInsurerByInsurerPackageName() {
		InsurerDetail insurer = new InsurerDetail(1, "Apollo", "HealthPack", 14000, 4);
		when(repo.findByName(Mockito.any())).thenReturn(Optional.of(insurer));
		assertTrue(service.getInsurerByInsurerPackageName("HealthPack").equals(insurer));
	}
}
