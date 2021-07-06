package com.cts.iptreatment.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "Authorizatiion-Microservice", url = "${auth.URL}")
public interface AuthorizationClient {
	
	@PostMapping(value = "/authorize")
	public boolean authorizeTheRequest(@RequestHeader(value = "Authorization", required = true) String requestTokenHeader);
}