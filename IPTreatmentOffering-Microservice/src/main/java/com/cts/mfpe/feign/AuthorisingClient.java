package com.cts.mfpe.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "Authorizatiion-Microservice", url = "http://localhost:8400/")
public interface AuthorisingClient {

	@PostMapping("/authorize")
	public ResponseEntity<String> authorizeTheRequest(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader);
}