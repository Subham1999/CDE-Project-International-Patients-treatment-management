package com.cts.insurance.claim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class InsuranceClaimApplication {
	public static void main(String[] args) {
		SpringApplication.run(InsuranceClaimApplication.class, args);
	}

}
