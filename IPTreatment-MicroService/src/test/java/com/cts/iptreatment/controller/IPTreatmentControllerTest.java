package com.cts.iptreatment.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.iptreatment.feign.AuthorizationClient;
import com.cts.iptreatment.feign.IPTreatmentOfferingClient;
import com.cts.iptreatment.model.AilmentCategory;
import com.cts.iptreatment.model.IPTreatmentPackage;
import com.cts.iptreatment.model.PackageDetail;
import com.cts.iptreatment.model.PatientDetails;
import com.cts.iptreatment.model.SpecialistDetail;
import com.cts.iptreatment.service.PatientDeatilsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@SpringBootTest
@AutoConfigureMockMvc
class IPTreatmentControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private PatientDeatilsService service;
	@MockBean
	private AuthorizationClient authorizationClient;
	@MockBean
	private IPTreatmentOfferingClient ipTreatmentOfferingClient;
	@Test
	void test1() {
		assertThat(service).isNotNull();
	}
	@Test
	void test2() {
		assertThat(authorizationClient).isNotNull();
	}
	@Test
	void test3() {
		assertThat(ipTreatmentOfferingClient).isNotNull();
	}
	@Test
	void getAllIPTreatmentPlanWithInValidTokenTest() throws Exception {
		when(authorizationClient.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		this.mockMvc.perform(get("/getAllTreatmentPlan").header("Authorization", "@WrongToken"))
				.andExpect(status().isForbidden());
	}
	@Test
	void getAllIPTreatmentPlanWithValidTokenTest() throws Exception {
		when(authorizationClient.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		this.mockMvc
				.perform(get("/getAllTreatmentPlan").header("Authorization", "@uthoriz@tionToken123"))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
	}
	@Test
	public void testAddPatientAndReturntreatmnetPlan() throws Exception {
		PatientDetails patient = new PatientDetails(1, "Patient1", 30, AilmentCategory.ORTHOPAIDICS, "Package 1", LocalDate.of(2021,03, 03));
		List<String> test = new ArrayList<>();
		test.add("OPT1");
		test.add("OPT2");
		PackageDetail package1 = new PackageDetail(101, "Package 1", test, 2500, 4);
		IPTreatmentPackage pack1 = new IPTreatmentPackage(1, AilmentCategory.ORTHOPAIDICS, package1);
		List<SpecialistDetail> list = new ArrayList<>();
		list.add(new SpecialistDetail(1, "Dr. Riya", AilmentCategory.ORTHOPAIDICS, 2, 11111111));
		list.add(new SpecialistDetail(2, "Dr. John", AilmentCategory.ORTHOPAIDICS, 0, 11111111));
		when(authorizationClient.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		when(ipTreatmentOfferingClient.getAllSpecialist("@uthoriz@tionToken123")).thenReturn(list);
		when(ipTreatmentOfferingClient.getIPTreatmentPackageByName(AilmentCategory.ORTHOPAIDICS, "Package 1","@uthoriz@tionToken123")).thenReturn(pack1);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		String jsonString = mapper.writeValueAsString(patient);
		this.mockMvc.perform(post("/formulateTimetable")
				.header("Authorization", "@uthoriz@tionToken123")
				.contentType(MediaType.APPLICATION_JSON).content(jsonString))
				.andExpect(status().isOk());
	}
	@Test
	public void testAddPatientWithInvalidToken() throws Exception {
		PatientDetails patient = new PatientDetails(1, "Patient1", 30, AilmentCategory.ORTHOPAIDICS, "Package 1", LocalDate.of(2021,03, 03));
		List<String> test = new ArrayList<>();
		test.add("OPT1");
		test.add("OPT2");
		PackageDetail package1 = new PackageDetail(101, "Package 1", test, 2500, 4);
		IPTreatmentPackage pack1 = new IPTreatmentPackage(1, AilmentCategory.ORTHOPAIDICS, package1);
		List<SpecialistDetail> list = new ArrayList<>();
		list.add(new SpecialistDetail(1, "Dr. Riya", AilmentCategory.ORTHOPAIDICS, 2, 11111111));
		list.add(new SpecialistDetail(2, "Dr. John", AilmentCategory.ORTHOPAIDICS, 0, 11111111));
		when(authorizationClient.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		when(ipTreatmentOfferingClient.getAllSpecialist("@uthoriz@tionToken123")).thenReturn(list);
		when(ipTreatmentOfferingClient.getIPTreatmentPackageByName(AilmentCategory.ORTHOPAIDICS, "Package 1","@uthoriz@tionToken123")).thenReturn(pack1);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		String jsonString = mapper.writeValueAsString(patient);
		this.mockMvc.perform(post("/formulateTimetable")
				.header("Authorization", "wrongToken")
				.contentType(MediaType.APPLICATION_JSON).content(jsonString))
				.andExpect(status().isForbidden());
	}
	@Test
	public void testUpdateStatus() throws Exception {
		PatientDetails patient = new PatientDetails(1, "Patient1", 30, AilmentCategory.ORTHOPAIDICS, "Package 1", LocalDate.of(2021,03, 03));
		List<String> test = new ArrayList<>();
		test.add("OPT1");
		test.add("OPT2");
		PackageDetail package1 = new PackageDetail(101, "Package 1", test, 2500, 4);
		IPTreatmentPackage pack1 = new IPTreatmentPackage(1, AilmentCategory.ORTHOPAIDICS, package1);
		List<SpecialistDetail> list = new ArrayList<>();
		list.add(new SpecialistDetail(1, "Dr. Riya", AilmentCategory.ORTHOPAIDICS, 2, 11111111));
		list.add(new SpecialistDetail(2, "Dr. John", AilmentCategory.ORTHOPAIDICS, 0, 11111111));
		when(authorizationClient.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		when(ipTreatmentOfferingClient.getAllSpecialist("@uthoriz@tionToken123")).thenReturn(list);
		when(ipTreatmentOfferingClient.getIPTreatmentPackageByName(AilmentCategory.ORTHOPAIDICS, "Package 1","@uthoriz@tionToken123")).thenReturn(pack1);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		String jsonString = mapper.writeValueAsString(patient);
		this.mockMvc.perform(post("/updateTreatmentPlan")
				.header("Authorization", "@uthoriz@tionToken123")
				.contentType(MediaType.APPLICATION_JSON).content(jsonString))
				.andExpect(status().isOk());
	}
	@Test
	public void testUpdateStatustWithInvalidToken() throws Exception {
		PatientDetails patient = new PatientDetails(1, "Patient1", 30, AilmentCategory.ORTHOPAIDICS, "Package 1", LocalDate.of(2021,03, 03));
		List<String> test = new ArrayList<>();
		test.add("OPT1");
		test.add("OPT2");
		PackageDetail package1 = new PackageDetail(101, "Package 1", test, 2500, 4);
		IPTreatmentPackage pack1 = new IPTreatmentPackage(1, AilmentCategory.ORTHOPAIDICS, package1);
		List<SpecialistDetail> list = new ArrayList<>();
		list.add(new SpecialistDetail(1, "Dr. Riya", AilmentCategory.ORTHOPAIDICS, 2, 11111111));
		list.add(new SpecialistDetail(2, "Dr. John", AilmentCategory.ORTHOPAIDICS, 0, 11111111));
		when(authorizationClient.authorizeTheRequest("@uthoriz@tionToken123")).thenReturn(true);
		when(ipTreatmentOfferingClient.getAllSpecialist("@uthoriz@tionToken123")).thenReturn(list);
		when(ipTreatmentOfferingClient.getIPTreatmentPackageByName(AilmentCategory.ORTHOPAIDICS, "Package 1","@uthoriz@tionToken123")).thenReturn(pack1);
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		String jsonString = mapper.writeValueAsString(patient);
		this.mockMvc.perform(post("/updateTreatmentPlan")
				.header("Authorization", "wrongToken")
				.contentType(MediaType.APPLICATION_JSON).content(jsonString))
				.andExpect(status().isForbidden());
	}
	@Test
    void heatlthCheck() throws Exception {
        this.mockMvc.perform(get("/health-check")).andExpect(status().isOk());
    }

}
