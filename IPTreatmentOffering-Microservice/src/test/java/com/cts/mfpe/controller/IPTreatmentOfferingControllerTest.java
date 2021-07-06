package com.cts.mfpe.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.mfpe.exception.IPTreatmentPackageNotFoundException;
import com.cts.mfpe.feign.AuthorisingClient;
import com.cts.mfpe.model.AilmentCategory;
import com.cts.mfpe.model.IPTreatmentPackage;
import com.cts.mfpe.model.PackageDetail;
import com.cts.mfpe.model.SpecialistDetail;
import com.cts.mfpe.service.IPTreatmentOfferingService;

@WebMvcTest(value = IPTreatmentOfferingController.class)
class IPTreatmentOfferingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private IPTreatmentOfferingService ipOfferingService;

	@MockBean
	AuthorisingClient client;

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
	@DisplayName("Test Authorising client")
	void testClientNotNull() {
		assertThat(client).isNotNull();
	}

	@Test
	@DisplayName("Test Mock MVC client")
	void testMockMvcNotNull() {
		assertThat(mockMvc).isNotNull();
	}

	@Test
	@DisplayName("Test IPTreatmentOfferingService client")
	void testServiceNotNull() {
		assertThat(ipOfferingService).isNotNull();
	}

	@Test
	@DisplayName("Test getAllIPTreatmentPackage() of the Controller with valid token ")
	void testGetAllIPTreatmentPackageWithValidToken() throws Exception {

		when(client.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		when(ipOfferingService.findAllIPTreatmentPackages()).thenReturn(Arrays.asList(pack1, pack2));

		this.mockMvc.perform(
				get("/ipTreatmentPackages").header("Authorization", "@uthoriz@tionToken123"))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));

		verify(ipOfferingService, times(1)).findAllIPTreatmentPackages();

	}

	@Test
	@DisplayName("Test getAllIPTreatmentPackage() of the Controller with invalid token ")
	void testGetAllIPTreatmentPackageTestWithInvalidToken() throws Exception {

		when(client.authorizeTheRequest("wrongtoken")).thenReturn(false);

		this.mockMvc.perform(get("/ipTreatmentPackages").header("Authorization", "wrongtoken"))
				.andExpect(status().isForbidden());

	}

	@Test
	@DisplayName("Test getAllIPTreatmentPackage() of the Controller without token ")
	void testGetAllIPTreatmentPackageTestWithoutHeader() throws Exception {

		this.mockMvc.perform(get("/ipTreatmentPackages")).andExpect(status().isBadRequest());

	}


	@Test
	@DisplayName("Test getIPTreatmentPackageByName() of the Controller with valid token")
	void testGetIPTreatmentPackageByNameWithValidToken() throws Exception {

		when(client.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		when(ipOfferingService.findIPTreatmentPackageByName(any(), anyString())).thenReturn(pack1);

		this.mockMvc
				.perform(get("/ipTreatmentPackageByName/{ailment}/{packageName}", "ORTHOPAIDICS",
						"Package 1").header("Authorization", "@uthoriz@tionToken123"))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				   .andExpect(jsonPath("$.treatmentPackageId", is(1)))
				   .andExpect(jsonPath("$.packageDetail.pid", is(101)));

	}
	
	@Test
	@DisplayName("Test invalid getIPTreatmentPackageByName() of the Controller with valid token")
	void testGetInvalidIPTreatmentPackageByNameWithValidToken() throws Exception {

		when(client.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		when(ipOfferingService.findIPTreatmentPackageByName(any(), anyString())).thenThrow(new IPTreatmentPackageNotFoundException("IP Treatment Package not found"));

		this.mockMvc
				.perform(get("/ipTreatmentPackageByName/{ailment}/{packageName}", "ORTHOPAIDICS",
						"Package 4").header("Authorization", "@uthoriz@tionToken123"))
				.andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("Test getIPTreatmentPackageByName() of the Controller with invalid token")
	void testGetIPTreatmentPackageByNameWithInvalidToken() throws Exception {

		when(client.authorizeTheRequest("wrongtoken")).thenReturn(false);

		this.mockMvc.perform(get("/ipTreatmentPackageByName/ORTHOPAIDICS/Package 1")
				.header("Authorization", "wrongtoken")).andExpect(status().isForbidden());

	}

	@Test
	@DisplayName("Test getIPTreatmentPackageByName() of the Controller without token")
	void testGetIPTreatmentPackageByNameTestWithoutHeader() throws Exception {

		this.mockMvc.perform(get("/ipTreatmentPackageByName/ORTHOPAIDICS/Package 1"))
				.andExpect(status().isBadRequest());
	}



	@Test
	@DisplayName("Test getAllSpecialist() of the Controller with valid token")
	void getAllSpecialistWithValidTokenTest() throws Exception {

		when(client.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		when(ipOfferingService.findAllSpecialists()).thenReturn(Arrays.asList(specialist1, specialist2));

		this.mockMvc.perform(get("/specialists").header("Authorization", "@uthoriz@tionToken123"))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));

	}

	@Test
	@DisplayName("Test getAllSpecialist() of the Controller with invalid token")
	void getAllSpecialistTestWithInvalidToken() throws Exception {

		when(client.authorizeTheRequest("wrongtoken")).thenReturn(false);

		this.mockMvc.perform(get("/specialists").header("Authorization", "wrongtoken"))
				.andExpect(status().isForbidden());

	}

	@Test
	@DisplayName("Test getAllSpecialist() of the Controller without token")
	void getAllSpecialistsTestWithoutHeader() throws Exception {

		this.mockMvc.perform(get("/specialists")).andExpect(status().isBadRequest());

	}
	@Test
    @DisplayName("Test healthCheck() of the Controller")
    void heatlthCheck() throws Exception {
        this.mockMvc.perform(get("/health-check")).andExpect(status().isOk());
    }

}
