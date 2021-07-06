package com.cts.iptreatment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.iptreatment.exception.AuthorizationException;
import com.cts.iptreatment.feign.AuthorizationClient;
import com.cts.iptreatment.model.PatientDetails;
import com.cts.iptreatment.model.TreatmentPlan;
import com.cts.iptreatment.service.PatientDeatilsService;

@RestController
public class IPTreatmentController {
	@Autowired
	private PatientDeatilsService service;
	@Autowired
	private AuthorizationClient client;

	/**
	 * @param patientDetails
	 * @param requestTokenHeader
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/formulateTimetable")
	public TreatmentPlan formulateTimetable(@RequestBody PatientDetails patientDetails,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception {
		System.out.println("Inside Formulate Timetable==================================================");
		if (client.authorizeTheRequest(requestTokenHeader)) {
			service.savePatientDetails(patientDetails);
			return service.saveTreatmentPlan(requestTokenHeader, patientDetails);
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}

	/**
	 * @param requestTokenHeader
	 * @return
	 * @throws AuthorizationException
	 */
	@GetMapping("/getAllTreatmentPlan")
	public List<TreatmentPlan> getAllTreatmentPlan(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws AuthorizationException {
		if (client.authorizeTheRequest(requestTokenHeader))
			return service.getAllTreatmentPlan();
		else {
			throw new AuthorizationException("Not allowed");
		}
	}

	/**
	 * @param treatmentPlan
	 * @param requestTokenHeader
	 * @return TreatmentPlan with status updated
	 * @throws Exception
	 */
	@PostMapping("/updateTreatmentPlan")
	public TreatmentPlan updateStatus(@RequestBody TreatmentPlan treatmentPlan,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception {
		if (client.authorizeTheRequest(requestTokenHeader))
			return service.updateStatus(treatmentPlan);
		else {
			throw new AuthorizationException("Not allowed");
		}
	}

	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("Ok", HttpStatus.OK);
	}

}
