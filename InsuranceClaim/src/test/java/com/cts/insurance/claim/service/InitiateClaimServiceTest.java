package com.cts.insurance.claim.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.insurance.claim.feign.IPTreatmentClient;
import com.cts.insurance.claim.model.AilmentCategory;
import com.cts.insurance.claim.model.InitiateClaim;
import com.cts.insurance.claim.model.InsurerDetail;
import com.cts.insurance.claim.model.PatientDetails;
import com.cts.insurance.claim.model.TreatmentPlan;
import com.cts.insurance.claim.repository.InitiateClaimRepository;
import com.cts.insurance.claim.repository.InsurerDetailRepository;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class InitiateClaimServiceTest {
	@Mock
	private InitiateClaimRepository repo;
	@Mock
	private InsurerDetailRepository insurerRepo;
	@Mock
	private IPTreatmentClient client;
	@InjectMocks
	private InitiateClaimServiceImpl service;

	@SuppressWarnings("deprecation")
	@BeforeEach
	void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Order(1)
	void testSaveInitiateReturnPositiveValueClaim() throws Exception {
		InitiateClaim claim = new InitiateClaim(1, "Patient1", AilmentCategory.ORTHOPAIDICS, "Apollo", 0.0);
		InsurerDetail insurer = new InsurerDetail(1, "Apollo", "Surgical Protection", 10000, 3);
		PatientDetails p1 = new PatientDetails(1, "Patient1", 30, AilmentCategory.ORTHOPAIDICS, "Package 1",
				LocalDate.of(2021, 03, 03));
		PatientDetails p2 = new PatientDetails(2, "Patient2", 30, AilmentCategory.ORTHOPAIDICS, "Package 2",
				LocalDate.of(2021, 03, 03));
		TreatmentPlan t1 = new TreatmentPlan(1, "Package 1", java.util.Arrays.asList("", ""), 100000.0, "Dr. Riya",
				LocalDate.of(2021, 03, 03), LocalDate.of(2021, 05, 03), "In-Progress", p1);
		TreatmentPlan t2 = new TreatmentPlan(1, "Package 1", java.util.Arrays.asList("", ""), 100000.0, "Dr. Riya",
				LocalDate.of(2021, 03, 03), LocalDate.of(2021, 05, 03), "In-Progress", p2);
		TreatmentPlan updatedT1 = new TreatmentPlan(1, "Package 1", java.util.Arrays.asList("", ""), 100000.0,
				"Dr. Riya", LocalDate.of(2021, 03, 03), LocalDate.of(2021, 05, 03), "Completed", p1);
		when(repo.save(claim)).thenReturn(claim);
		when(insurerRepo.findByInsurerName("Apollo")).thenReturn(Optional.of(insurer));
		when(client.getAllTreatmentPlan("token")).thenReturn(Arrays.asList(t1, t2));
		when(client.updateStatus(t1, "token")).thenReturn(updatedT1);
		assertThat(service.initiateClaim(claim, "token")).isEqualTo(90000.0);
	}

	@Test
	@Order(2)
	void testFindByName() throws Exception {
		InitiateClaim claim = new InitiateClaim(1, "Patient1", AilmentCategory.ORTHOPAIDICS, "Apollo", 0);
		InsurerDetail insurer = new InsurerDetail(1, "Apollo", "Surgical Protection", 1000000, 3);
		PatientDetails p1 = new PatientDetails(1, "Patient1", 30, AilmentCategory.ORTHOPAIDICS, "Package 1",
				LocalDate.of(2021, 03, 03));
		PatientDetails p2 = new PatientDetails(2, "Patient2", 30, AilmentCategory.ORTHOPAIDICS, "Package 2",
				LocalDate.of(2021, 03, 03));
		TreatmentPlan t1 = new TreatmentPlan(1, "Package 1", java.util.Arrays.asList("", ""), 10000.0, "Dr. Riya",
				LocalDate.of(2021, 03, 03), LocalDate.of(2021, 05, 03), "In-Progress", p1);
		TreatmentPlan t2 = new TreatmentPlan(1, "Package 1", java.util.Arrays.asList("", ""), 10000.0, "Dr. Riya",
				LocalDate.of(2021, 03, 03), LocalDate.of(2021, 05, 03), "In-Progress", p2);
		TreatmentPlan updatedT1 = new TreatmentPlan(1, "Package 1", java.util.Arrays.asList("", ""), 10000.0,
				"Dr. Riya", LocalDate.of(2021, 03, 03), LocalDate.of(2021, 05, 03), "Completed", p1);
		when(repo.save(claim)).thenReturn(claim);
		when(client.getAllTreatmentPlan("token")).thenReturn(Arrays.asList(t1, t2));
		when(client.updateStatus(t1, "token")).thenReturn(updatedT1);
		when(insurerRepo.findByInsurerName("Apollo")).thenReturn(Optional.of(insurer));
		assertThat(service.initiateClaim(claim, "token")).isEqualTo(0);
	}
}
