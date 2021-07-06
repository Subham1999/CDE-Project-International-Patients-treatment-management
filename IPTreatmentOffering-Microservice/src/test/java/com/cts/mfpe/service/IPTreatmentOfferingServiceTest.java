package com.cts.mfpe.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.mfpe.exception.IPTreatmentPackageNotFoundException;
import com.cts.mfpe.model.AilmentCategory;
import com.cts.mfpe.model.IPTreatmentPackage;
import com.cts.mfpe.model.PackageDetail;
import com.cts.mfpe.model.SpecialistDetail;
import com.cts.mfpe.repository.IPTreatmentPackageRepository;
import com.cts.mfpe.repository.SpecialistDetailRepository;

class IPTreatmentOfferingServiceTest {

	@Mock
	IPTreatmentPackageRepository treatmentPackageRepository;

	@Mock
	SpecialistDetailRepository specialistRepository;

	@InjectMocks
	IPTreatmentOfferingServiceImpl ipOfferingService;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	private PackageDetail package1;
	private IPTreatmentPackage pack1;
	private PackageDetail package2 ;
	private IPTreatmentPackage pack2;
	private SpecialistDetail specialist1;
	private SpecialistDetail specialist2;
	
	@BeforeEach
	public void setup() {
		
		package1 = new PackageDetail(101, "Package 1", Arrays.asList("OPT1", "OPT2"), 2500, 4);
		pack1 = new IPTreatmentPackage(1, AilmentCategory.ORTHOPAIDICS, package1);
		package2 = new PackageDetail(102, "Package 2", Arrays.asList("OPT3", "OPT4"), 3000, 6);
		pack2 = new IPTreatmentPackage(1, AilmentCategory.ORTHOPAIDICS, package2);
		specialist1 = new SpecialistDetail(201, "Dr Riya", AilmentCategory.ORTHOPAIDICS, 10, 9876543210L);
		specialist2 = new SpecialistDetail(202, "Dr Karan", AilmentCategory.UROLOGY, 8, 9223344556L);
	}
	
	@Test
	@DisplayName("Test findAllIPTreatmentPackages() of IPTreatmentPackageService")
	public void testfindAllIPTreatmentPackages() {

		when(treatmentPackageRepository.findAll()).thenReturn(Arrays.asList(pack1, pack2));
		assertThat(ipOfferingService.findAllIPTreatmentPackages()).hasSize(2);
	}

	@Test
	@DisplayName("Test findIPTreatmentPackageByName() of IPTreatmentPackageService")
	public void testValidfindIPTreatmentPackageByName() throws IPTreatmentPackageNotFoundException {

		when(treatmentPackageRepository.findByName(any(), anyString())).thenReturn(Optional.of(pack1));
		assertTrue(ipOfferingService.findIPTreatmentPackageByName(AilmentCategory.ORTHOPAIDICS, "Package 1").getPackageDetail().equals(package1));
	}

	@Test
	@DisplayName("Test invalid findIPTreatmentPackageByName() of IPTreatmentPackageService")
	public void testInValidfindIPTreatmentPackageByName() throws IPTreatmentPackageNotFoundException {

		when(treatmentPackageRepository.findByName(any(), anyString())).thenReturn(Optional.empty());
		assertThrows(IPTreatmentPackageNotFoundException.class, () -> {
			ipOfferingService.findIPTreatmentPackageByName(AilmentCategory.ORTHOPAIDICS, "Package 4");
		});
		
		verify(treatmentPackageRepository).findByName(AilmentCategory.ORTHOPAIDICS, "Package 4");
	}

	@Test
	@DisplayName("Test findAllSpecialists() of SpecialistDetailService")
	public void testFindAllSpecialists() {

		when(specialistRepository.findAll()).thenReturn(Arrays.asList(specialist1, specialist2));
		assertThat(ipOfferingService.findAllSpecialists()).hasSize(2);
		verify(specialistRepository).findAll();
	}
}
