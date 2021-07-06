package com.cts.iptreatment.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.iptreatment.exception.PatientNameAlreadyExistsException;
import com.cts.iptreatment.feign.IPTreatmentOfferingClient;
import com.cts.iptreatment.model.AilmentCategory;
import com.cts.iptreatment.model.IPTreatmentPackage;
import com.cts.iptreatment.model.PackageDetail;
import com.cts.iptreatment.model.PatientDetails;
import com.cts.iptreatment.model.SpecialistDetail;
import com.cts.iptreatment.model.TreatmentPlan;
import com.cts.iptreatment.repository.PatientDetailsRepository;
import com.cts.iptreatment.repository.TreatmentPlanRepository;

@SpringBootTest
class PatientDetailsServiceTest {
	@Mock
	private TreatmentPlanRepository treatmentPlanRepo;
	@Mock
	private PatientDetailsRepository patientRepo;
	@Mock
	private IPTreatmentOfferingClient client;
	@InjectMocks
	private PatientDeatilsService service;
	@Test
	@DisplayName("Test TreatmentPlanRepository mock")
	public void treatmentPlanRepoNotNullTest() {
		assertThat(treatmentPlanRepo).isNotNull();
	}
	@Test
	@DisplayName("Test PatientDetailsRepository mock")
	public void patientRepoNotNullTest() {
		assertThat(patientRepo).isNotNull();
	}
	@Test
	@DisplayName("Test PatientDeatilsService mock")
	public void serviceNotNullTest() {
		assertThat(service).isNotNull();
	}
	@Test
	public void formulateTreatmentThrowPatientNameAlreadyExistsExceptionTest() {
		PatientDetails patientDetails = new PatientDetails(1, "Patient1", 30, AilmentCategory.ORTHOPAIDICS, "Package 1",
				LocalDate.of(2021, 03, 03));
		TreatmentPlan treatmentPlan = new TreatmentPlan(1, "Package 1", java.util.Arrays.asList("", ""), 10000.0, "Dr. Riya",
				LocalDate.of(2021, 03, 03), LocalDate.of(2021, 05, 03), "In-Progress", patientDetails);
		when(treatmentPlanRepo.findAll()).thenReturn(Arrays.asList(treatmentPlan));
		assertThrows(PatientNameAlreadyExistsException.class, () -> {
			service.saveTreatmentPlan("token", patientDetails);
		});
	}
	@Test
	public void formulateTreatmentDoNotThrowPatientNameAlreadyExistsExceptionTest() throws Exception {
		PatientDetails patientDetails1 = new PatientDetails(1, "Patient1", 30, AilmentCategory.ORTHOPAIDICS, "Package 1",
				LocalDate.of(2021, 03, 03));
		PatientDetails patientDetails2 = new PatientDetails(2, "Patient2", 30, AilmentCategory.ORTHOPAIDICS, "Package 2",
				LocalDate.of(2021, 03, 03));
		TreatmentPlan treatmentPlan1 = new TreatmentPlan(1, "Package 1", java.util.Arrays.asList("", ""), 10000.0, "Dr. Riya",
				LocalDate.of(2021, 03, 03), LocalDate.of(2021, 05, 03), "In-Progress", patientDetails1);
		TreatmentPlan treatmentPlan2 = new TreatmentPlan(1, "Package 1", java.util.Arrays.asList("", ""), 10000.0, "Dr. Riya",
				LocalDate.of(2021, 03, 03), LocalDate.of(2021, 05, 03), "In-Progress", patientDetails2);
		PackageDetail package1 = new PackageDetail(101, "Package 1", Arrays.asList("OPT1", "OPT2"), 2500, 4);
		IPTreatmentPackage pack1 = new IPTreatmentPackage(1, AilmentCategory.ORTHOPAIDICS, package1);
		PackageDetail package2 = new PackageDetail(102, "Package 2", Arrays.asList("OPT3", "OPT4"), 3000, 6);
		IPTreatmentPackage pack2 = new IPTreatmentPackage(2, AilmentCategory.ORTHOPAIDICS, package2);
		SpecialistDetail detail = new SpecialistDetail(1, "Dr. Riya", AilmentCategory.ORTHOPAIDICS, 4, 11111111);
		when(treatmentPlanRepo.findAll()).thenReturn(Arrays.asList(treatmentPlan1));
		when(client.getAllIPTreatmentPackage("token")).thenReturn(java.util.Arrays.asList(pack1, pack2));
		when(client.getIPTreatmentPackageByName(AilmentCategory.ORTHOPAIDICS, "Package 1", "token")).thenReturn(pack1);
		when(client.getIPTreatmentPackageByName(AilmentCategory.ORTHOPAIDICS, "Package 2", "token")).thenReturn(pack2);
		when(client.getAllSpecialist("token")).thenReturn(Arrays.asList(detail));
		assertThat(service.saveTreatmentPlan("token", patientDetails2)).isNull();
	}
	@Test
	public void formulateTreatmentPlanTest() {
		PatientDetails patientDetails = new PatientDetails(1, "Patient1", 30, AilmentCategory.ORTHOPAIDICS, "Package 1",
				LocalDate.of(2021, 03, 03));
		TreatmentPlan treatmentPlan = new TreatmentPlan(1, "Package 1", java.util.Arrays.asList("", ""), 10000.0, "Dr. Riya",
				LocalDate.of(2021, 03, 03), LocalDate.of(2021, 05, 03), "In-Progress", patientDetails);
		when(patientRepo.save(Mockito.any())).thenReturn(patientDetails);
		when(treatmentPlanRepo.save(Mockito.any())).thenReturn(treatmentPlan);
		assertThat(service.savePatientDetails(patientDetails).equals(treatmentPlan));
	}
	@Test
	public void getAllTreatmentPlanTest() {
		PatientDetails patientDetails = new PatientDetails(1, "Patient1", 30, AilmentCategory.ORTHOPAIDICS, "Package 1",
				LocalDate.of(2021, 03, 03));
		TreatmentPlan treatmentPlan = new TreatmentPlan(1, "Package 1", java.util.Arrays.asList("", ""), 10000.0, "Dr. Riya",
				LocalDate.of(2021, 03, 03), LocalDate.of(2021, 05, 03), "In-Progress", patientDetails);
		when(treatmentPlanRepo.findAll()).thenReturn(Arrays.asList(treatmentPlan));
		assertThat(service.getAllTreatmentPlan().get(0).equals(treatmentPlan));
	}
	@Test
	public void updateStatusTest() {
		PatientDetails patientDetails = new PatientDetails(1, "Patient1", 30, AilmentCategory.ORTHOPAIDICS, "Package 1",
				LocalDate.of(2021, 03, 03));
		TreatmentPlan treatmentPlan = new TreatmentPlan(1, "Package 1", java.util.Arrays.asList("", ""), 10000.0, "Dr. Riya",
				LocalDate.of(2021, 03, 03), LocalDate.of(2021, 05, 03), "Completed", patientDetails);
		when(treatmentPlanRepo.save(Mockito.any())).thenReturn(treatmentPlan);
		assertThat(service.updateStatus(treatmentPlan).equals(treatmentPlan));
	}
	@Test
	public void checkIfPatientAlreadyRegisteredShouldreturnTrueTest() {
		PatientDetails patientDetails = new PatientDetails(1, "Patient1", 30, AilmentCategory.ORTHOPAIDICS, "Package 1",
				LocalDate.of(2021, 03, 03));
		TreatmentPlan treatmentPlan = new TreatmentPlan(1, "Package 1", java.util.Arrays.asList("", ""), 10000.0, "Dr. Riya",
				LocalDate.of(2021, 03, 03), LocalDate.of(2021, 05, 03), "In-Progress", patientDetails);
		when(treatmentPlanRepo.findAll()).thenReturn(Arrays.asList(treatmentPlan));
		assertThat(service.checkIfPatientAlreadyRegistered("Patient1")).isTrue();
	}
	@Test
	public void checkIfPatientAlreadyRegisteredShouldreturnFalseTest() {
		PatientDetails patientDetails = new PatientDetails(1, "Patient1", 30, AilmentCategory.ORTHOPAIDICS, "Package 1",
				LocalDate.of(2021, 03, 03));
		TreatmentPlan treatmentPlan = new TreatmentPlan(1, "Package 1", java.util.Arrays.asList("", ""), 10000.0, "Dr. Riya",
				LocalDate.of(2021, 03, 03), LocalDate.of(2021, 05, 03), "In-Progress", patientDetails);
		when(treatmentPlanRepo.findAll()).thenReturn(Arrays.asList(treatmentPlan));
		assertThat(service.checkIfPatientAlreadyRegistered("Patient2")).isFalse();
	}
	@Test
	public void findSpecialistTest1() throws Exception {
		PatientDetails patientDetails = new PatientDetails(1, "Patient1", 30, AilmentCategory.ORTHOPAIDICS, "Package 1",
				LocalDate.of(2021, 03, 03));
		TreatmentPlan treatmentPlan = new TreatmentPlan(1, "Package 1", java.util.Arrays.asList("", ""), 10000.0, "Dr. Riya",
				LocalDate.of(2021, 03, 03), LocalDate.of(2021, 05, 03), "In-Progress", patientDetails);
		SpecialistDetail detail = new SpecialistDetail(1, "Dr. Riya", AilmentCategory.ORTHOPAIDICS, 4, 11111111);
		when(client.getAllSpecialist("token")).thenReturn(Arrays.asList(detail));
		when(treatmentPlanRepo.findAll()).thenReturn(Arrays.asList(treatmentPlan));
		assertThat(service.findASpecialist("token", AilmentCategory.ORTHOPAIDICS, "Package 1")).isNotNull();
	}
	@Test
	public void findSpecialistTest2() throws Exception {
		PatientDetails patientDetails = new PatientDetails(1, "Patient1", 30, AilmentCategory.ORTHOPAIDICS, "Package 2",
				LocalDate.of(2021, 03, 03));
		TreatmentPlan treatmentPlan = new TreatmentPlan(1, "Package 2", java.util.Arrays.asList("", ""), 10000.0, "Dr. Riya",
				LocalDate.of(2021, 03, 03), LocalDate.of(2021, 05, 03), "In-Progress", patientDetails);
		SpecialistDetail detail = new SpecialistDetail(1, "Dr. Riya", AilmentCategory.ORTHOPAIDICS, 4, 11111111);
		when(client.getAllSpecialist("token")).thenReturn(Arrays.asList(detail));
		when(treatmentPlanRepo.findAll()).thenReturn(Arrays.asList(treatmentPlan));
		assertThat(service.findASpecialist("token", AilmentCategory.ORTHOPAIDICS, "Package 2")).isNotNull();
	}
	@Test
	public void calculateEndDate() throws Exception {
		LocalDate today = LocalDate.now();
		LocalDate end = today.plus(2, ChronoUnit.WEEKS);
		assertThat(service.calculateEndDate(today, 2).equals(end));
	}
	@Test
	public void getPackageByNameTest() throws Exception {
		PackageDetail package1 = new PackageDetail(101, "Package 1", Arrays.asList("OPT1", "OPT2"), 2500, 4);
		IPTreatmentPackage pack1 = new IPTreatmentPackage(1, AilmentCategory.ORTHOPAIDICS, package1);
		SpecialistDetail detail = new SpecialistDetail(1, "Dr. Riya", AilmentCategory.ORTHOPAIDICS, 4, 11111111);
		when(client.getIPTreatmentPackageByName(AilmentCategory.ORTHOPAIDICS, "Package 1", "token")).thenReturn(pack1);
		assertThat(service.getPackageByName("token", AilmentCategory.ORTHOPAIDICS, "Package 1")).isNotNull();
	}
}
