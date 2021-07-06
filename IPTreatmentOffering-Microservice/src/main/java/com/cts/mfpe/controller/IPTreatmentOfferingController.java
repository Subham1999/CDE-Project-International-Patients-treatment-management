package com.cts.mfpe.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.mfpe.exception.AuthorizationException;
import com.cts.mfpe.exception.IPTreatmentPackageNotFoundException;
import com.cts.mfpe.feign.AuthorisingClient;
import com.cts.mfpe.model.AilmentCategory;
import com.cts.mfpe.model.IPTreatmentPackage;
import com.cts.mfpe.model.SpecialistDetail;
import com.cts.mfpe.repository.IPTreatmentPackageRepository;
import com.cts.mfpe.repository.SpecialistDetailRepository;
import com.cts.mfpe.service.IPTreatmentOfferingService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
public class IPTreatmentOfferingController {

	@Autowired
	private IPTreatmentOfferingService ipOfferingService;

	@Autowired
	private AuthorisingClient authorisingClient;

	@Autowired
	private SpecialistDetailRepository specialistDetailRepository;

	@Autowired
	private IPTreatmentPackageRepository ipTreatmentPackageRepository;

	/**
	 * @param requestTokenHeader
	 * @return
	 * @throws AuthorizationException
	 * @throws Exception
	 */
	@GetMapping("/ipTreatmentPackages")
	@ApiOperation(notes = "Returns the list of IP Treatment Packages", value = "Find IP Treatment Package")
	public List<IPTreatmentPackage> getAllIPTreatmentPackage(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws AuthorizationException {
		HttpStatus statusCode = authorisingClient.authorizeTheRequest(requestTokenHeader).getStatusCode();
		if (statusCode.value() >= 200 && statusCode.value() <= 299) {
			return ipOfferingService.findAllIPTreatmentPackages();
		} else {
			throw new AuthorizationException("Not allowed");
		}

	}

	/**
	 * @param ailment
	 * @param packageName
	 * @param requestTokenHeader
	 * @return
	 * @throws AuthorizationException
	 * @throws IPTreatmentPackageNotFoundException
	 * @throws Exception
	 */
	@GetMapping("/ipTreatmentPackageByName/{ailment}/{packageName}")
	@ApiOperation(notes = "Returns the IP Treatment Package based on package name", value = "Find IP Treatment Package by name")
	public IPTreatmentPackage getIPTreatmentPackageByName(
			@ApiParam(name = "ailment", value = "ailment of the package") @PathVariable AilmentCategory ailment,
			@ApiParam(name = "packageName", value = "name of the package") @PathVariable String packageName,
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws AuthorizationException, IPTreatmentPackageNotFoundException {

		HttpStatus statusCode = authorisingClient.authorizeTheRequest(requestTokenHeader).getStatusCode();
		if (statusCode.value() >= 200 && statusCode.value() <= 299) {
			return ipOfferingService.findIPTreatmentPackageByName(ailment, packageName);
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}

	/**
	 * @param requestTokenHeader
	 * @return
	 * @throws AuthorizationException
	 * @throws Exception
	 */
	@GetMapping("/specialists")
	@ApiOperation(notes = "Returns the list of specialists along with their experience and contact details", value = "Find specialists")
	public List<SpecialistDetail> getAllSpecialist(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader)
			throws AuthorizationException {
		System.out.println("Inside ================" + requestTokenHeader);
		ResponseEntity<String> authorizeTheRequest = authorisingClient.authorizeTheRequest(requestTokenHeader);
		System.err.println(authorizeTheRequest.getStatusCodeValue() + " " + authorizeTheRequest.getBody());
		HttpStatus statusCode = authorizeTheRequest.getStatusCode();
		if (statusCode.value() >= 200 && statusCode.value() <= 299) {
			return ipOfferingService.findAllSpecialists();
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}

	@GetMapping("/specialistsByExpertise/{areaOfExpertise}")
	public ResponseEntity<?> getSpecialistsByAreaOfExpertise(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@PathVariable String areaOfExpertise) throws AuthorizationException {

		HttpStatus statusCode = authorisingClient.authorizeTheRequest(requestTokenHeader).getStatusCode();
		if (statusCode.value() >= 200 && statusCode.value() <= 299) {

			List<SpecialistDetail> specialistsByExpertise = ipOfferingService.findAllSpecialists().stream()
					.filter(x -> x.getAreaOfExpertise().toString().equalsIgnoreCase(areaOfExpertise))
					.collect(Collectors.toUnmodifiableList());

			return ResponseEntity.status(HttpStatus.OK).body(specialistsByExpertise);
		} else {
			throw new AuthorizationException("Not allowed");
		}
	}

	@PostMapping("/addSpecialist")
	public ResponseEntity<?> addSpecialist(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@Valid @RequestBody SpecialistDetail specialistDetail) throws AuthorizationException {

		HttpStatus statusCode = authorisingClient.authorizeTheRequest(requestTokenHeader).getStatusCode();
		if (statusCode.value() >= 200 && statusCode.value() <= 299) {

			specialistDetail = specialistDetailRepository.save(specialistDetail);
			return ResponseEntity.status(HttpStatus.OK).body(specialistDetail);
		} else {
			throw new AuthorizationException("Not allowed");
		}

	}

	@DeleteMapping("/deleteSpecialist/{specialistId}")
	public ResponseEntity<?> deleteSpecialist(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@PathVariable int specialistId) throws AuthorizationException {

		HttpStatus statusCode = authorisingClient.authorizeTheRequest(requestTokenHeader).getStatusCode();
		if (statusCode.value() >= 200 && statusCode.value() <= 299) {
			try {
				specialistDetailRepository.deleteById(specialistId);

				return ResponseEntity.ok().build();
			} catch (IllegalArgumentException exception) {
				return ResponseEntity.badRequest().body("invalid specialistId");
			}
		} else {
			throw new AuthorizationException("Not allowed");
		}

	}

	@PutMapping("/updatePackage/{packageId}")
	public ResponseEntity<?> updatePackage(
			@RequestHeader(value = "Authorization", required = true) String requestTokenHeader,
			@PathVariable int packageId, @Valid @RequestBody IPTreatmentPackage ipTreatmentPackage)
			throws AuthorizationException {

		HttpStatus statusCode = authorisingClient.authorizeTheRequest(requestTokenHeader).getStatusCode();
		if (statusCode.value() >= 200 && statusCode.value() <= 299) {
			try {
				if (ipTreatmentPackage.getTreatmentPackageId() == packageId) {
					ipTreatmentPackageRepository.save(ipTreatmentPackage);
					return ResponseEntity.ok().build();
				} else {
					return ResponseEntity.badRequest().body("packageId mismatch");
				}
			} catch (IllegalArgumentException exception) {
				return ResponseEntity.badRequest().body("invalid specialistId");
			}
		} else {
			throw new AuthorizationException("Not allowed");
		}

	}

	@GetMapping("/health-check")
	public ResponseEntity<String> healthCheck() {
		return new ResponseEntity<>("Ok", HttpStatus.OK);
	}
}
