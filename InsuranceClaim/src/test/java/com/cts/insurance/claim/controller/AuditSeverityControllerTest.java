package com.cts.insurance.claim.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.insurance.claim.feign.AuthorisingClient;
import com.cts.insurance.claim.model.AilmentCategory;
import com.cts.insurance.claim.model.InitiateClaim;
import com.cts.insurance.claim.model.InsurerDetail;
import com.cts.insurance.claim.service.AuditSevertyService;
import com.cts.insurance.claim.service.InitiateClaimService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
@AutoConfigureMockMvc
class AuditSeverityControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AuditSevertyService auditSevertyService;
	@MockBean
	private InitiateClaimService initiateClaimService;
	@MockBean
	AuthorisingClient client;

	@Test
	void notNull() {
		assertThat(client).isNotNull();
	}

	@Test
	void notNull1() {
		assertThat(mockMvc).isNotNull();
	}

	@Test
	void notNull2() {
		assertThat(auditSevertyService).isNotNull();
	}

	@Test
	void notNull3() {
		assertThat(initiateClaimService).isNotNull();
	}

	@Test
	void getAllInsurersWithValidToken() throws Exception {
		when(client.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		when(auditSevertyService.getAllInsurers()).thenReturn(new ArrayList<>());

		this.mockMvc.perform(get("/getAllInsurerDetail").header("Authorization", "@uthoriz@tionToken123"))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));

	}

	@Test
	void getAllInsurersWithInValidToken() throws Exception {
		when(client.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		this.mockMvc.perform(get("/getAllInsurerDetail").header("Authorization", "WrongToken"))
				.andExpect(status().isForbidden());
	}

	@Test
	void getAllInsurersWithoutHeader() throws Exception {
		when(client.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		this.mockMvc.perform(get("/getAllInsurerDetail")).andExpect(status().isBadRequest());
	}

	@Test
	void getInsurerByPackageNameWithValidToken() throws Exception {
		InsurerDetail insurer = new InsurerDetail(1, "Apollo", "HealthPack", 14000, 4);
		when(client.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		when(auditSevertyService.getInsurerByInsurerPackageName("HealthPack")).thenReturn(insurer);
		this.mockMvc.perform(get("/getInsurerByPackageName/{packageName}", "HealthPack").header("Authorization",
				"@uthoriz@tionToken123")).andExpect(status().isOk());
	}

	@Test
	void getInsurerByPackageNameWithInValidToken() throws Exception {
		when(client.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		this.mockMvc.perform(
				get("/getInsurerByPackageName/{packageName}", "HealthPack").header("Authorization", "WrongToken"))
				.andExpect(status().isForbidden());
	}

	@Test
	void getInsurerByPackageNameWithoutHeader() throws Exception {
		when(client.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		this.mockMvc.perform(get("/getInsurerByPackageName/{packageName}", "HealthPack"))
				.andExpect(status().isBadRequest());
	}

	@Test
	void initiateClaimWithValidToken() throws Exception {
		InitiateClaim claim = new InitiateClaim(1, "patient2", AilmentCategory.ORTHOPAIDICS, "Apollo", 1000);
		when(client.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		when(initiateClaimService.initiateClaim(claim, "@uthoriz@tionToken123")).thenReturn(1000.0);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		String jsonString = mapper.writeValueAsString(claim);
		this.mockMvc.perform(post("/initiateClaim").header("Authorization", "@uthoriz@tionToken123")
				.contentType(MediaType.APPLICATION_JSON).content(jsonString)).andExpect(status().isOk());
	}

	@Test
	void initiateClaimWithInValidToken() throws Exception {
		InitiateClaim claim = new InitiateClaim(1, "patient2", AilmentCategory.ORTHOPAIDICS, "Apollo", 1000);
		when(client.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		when(initiateClaimService.initiateClaim(claim, "@uthoriz@tionToken123")).thenReturn(1000.0);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		String jsonString = mapper.writeValueAsString(claim);
		this.mockMvc.perform(post("/initiateClaim").header("Authorization", "WrongToken")
				.contentType(MediaType.APPLICATION_JSON).content(jsonString)).andExpect(status().isForbidden());
	}

	@Test
	void initiateClaimWithoutHeader() throws Exception {
		when(client.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		this.mockMvc.perform(post("/initiateClaim")).andExpect(status().isBadRequest());
	}
	@Test
    void heatlthCheck() throws Exception {
        this.mockMvc.perform(get("/health-check")).andExpect(status().isOk());
    }

}
