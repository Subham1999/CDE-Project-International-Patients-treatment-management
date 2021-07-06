package com.cts.insurance.claim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.insurance.claim.exception.AuthorizationException;
import com.cts.insurance.claim.feign.AuthorisingClient;
import com.cts.insurance.claim.model.InitiateClaim;
import com.cts.insurance.claim.model.InsurerDetail;
import com.cts.insurance.claim.service.AuditSevertyService;
import com.cts.insurance.claim.service.InitiateClaimService;

@RestController
public class AuditSeverityController {
	@Autowired
	private AuditSevertyService auditSevertyService;
	@Autowired
	private InitiateClaimService initiateClaimService;
	@Autowired
	private AuthorisingClient authorizingClient;

	// To get all the insurer details
	@GetMapping("/getAllInsurerDetail")
	public List<InsurerDetail> getAllInsurers(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws AuthorizationException {
		if (authorizingClient.authorizeTheRequest(requestTokenHeader)) {
			return auditSevertyService.getAllInsurers();
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}

	// To get the insurer details based on the package name
	@GetMapping("/getInsurerByPackageName/{packageName}")
	public InsurerDetail getInsurerByPackageName(@PathVariable String packageName,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws AuthorizationException {
		if (authorizingClient.authorizeTheRequest(requestTokenHeader)) {
			return auditSevertyService.getInsurerByInsurerPackageName(packageName);
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}

	// To initiate the claim
	@PostMapping("/initiateClaim")
	public double initiateClaim(@RequestBody InitiateClaim claim,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception {
		if (authorizingClient.authorizeTheRequest(requestTokenHeader)) {
			return initiateClaimService.initiateClaim(claim, requestTokenHeader);
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}

	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("insure-Ok", HttpStatus.OK);
	}
}
