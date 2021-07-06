package com.cts.portal.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.portal.model.InitiateClaim;
import com.cts.portal.model.InsurerDetail;

@FeignClient(name = "InsuranceClaim", url = "${insure.URL}")
public interface InsuranceClaimClient {
    
	@GetMapping("/getAllInsurerDetail")
	public List<InsurerDetail> getAllInsurers(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception; 
	
	@GetMapping("/getInsurerByPackageName/{packageName}")
	public InsurerDetail getInsurerByPackageName(@PathVariable String packageName, @RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception;

	@PostMapping("/initiateClaim")
	public double initiateClaim(@RequestBody InitiateClaim claim, @RequestHeader(value = "Authorization", required = true) String requestTokenHeader) throws Exception;

}