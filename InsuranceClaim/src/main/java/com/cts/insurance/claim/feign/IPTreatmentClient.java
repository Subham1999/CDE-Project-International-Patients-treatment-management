package com.cts.insurance.claim.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.insurance.claim.exception.AuthorizationException;
import com.cts.insurance.claim.exception.IPTreatmentPackageNotFoundException;
import com.cts.insurance.claim.exception.PatientNameAlreadyExistsException;
import com.cts.insurance.claim.model.PatientDetails;
import com.cts.insurance.claim.model.TreatmentPlan;

@FeignClient(name = "IPTreatment-service", url = "${iptreatment.URL}")
public interface IPTreatmentClient {
    
	@PostMapping("/formulateTimetable")
	public TreatmentPlan formulateTimetable(@RequestBody PatientDetails patientDetails,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws PatientNameAlreadyExistsException, AuthorizationException, IPTreatmentPackageNotFoundException;
	
	@GetMapping("/getAllTreatmentPlan")
	public List<TreatmentPlan> getAllTreatmentPlan(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws AuthorizationException;
	
	@PostMapping("/updateTreatmentPlan")
	public TreatmentPlan updateStatus(@RequestBody TreatmentPlan treatmentPlan,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws AuthorizationException;
	
}