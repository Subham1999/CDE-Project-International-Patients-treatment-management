package com.cts.portal.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.portal.model.PatientDetails;
import com.cts.portal.model.TreatmentPlan;

@FeignClient(name = "IPTreatment-service", url = "${iptreatment.URL}")
public interface IPTreatmentClient {
    
	@PostMapping("/formulateTimetable")
	public TreatmentPlan formulateTimetable(@RequestBody PatientDetails patientDetails,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception;
	
	@GetMapping("/getAllTreatmentPlan")
	public List<TreatmentPlan> getAllTreatmentPlan(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception;
	
	@PostMapping("/updateTreatmentPlan")
	public TreatmentPlan updateStatus(@RequestBody TreatmentPlan treatmentPlan,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception;
	
}