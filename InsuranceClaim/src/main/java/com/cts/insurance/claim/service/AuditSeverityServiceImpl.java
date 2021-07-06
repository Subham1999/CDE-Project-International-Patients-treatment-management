package com.cts.insurance.claim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.insurance.claim.model.InsurerDetail;
import com.cts.insurance.claim.repository.InsurerDetailRepository;

import lombok.extern.slf4j.Slf4j;

@Service @Slf4j
public class AuditSeverityServiceImpl implements AuditSevertyService{
	@Autowired
	private InsurerDetailRepository repo;
	@Override
	//Returns a list of all insurers based on the insurance details
	public List<InsurerDetail> getAllInsurers() {
		return repo.findAll();
	}
	//To add the insurer details 
	@Override
	public InsurerDetail addInsurers(InsurerDetail insurer) {
		log.info("[Insurers]"+insurer.toString());
		return repo.save(insurer);
	}
	//Get insurer object based on the insurer package name 
	@Override
	public InsurerDetail getInsurerByInsurerPackageName(String insurerPackageName) {
		return repo.findByName(insurerPackageName).orElse(null);
	}

}
