package com.cts.mfpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class IpTreatmentOfferingApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpTreatmentOfferingApplication.class, args);
	}
}
