package com.cts.insurance.claim.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.insurance.claim.exception.AuthorizationException;
import com.cts.insurance.claim.feign.IPTreatmentClient;
import com.cts.insurance.claim.model.InitiateClaim;
import com.cts.insurance.claim.model.InsurerDetail;
import com.cts.insurance.claim.model.TreatmentPlan;
import com.cts.insurance.claim.repository.InitiateClaimRepository;
import com.cts.insurance.claim.repository.InsurerDetailRepository;

import lombok.extern.slf4j.Slf4j;

@Service @Slf4j
public class InitiateClaimServiceImpl implements InitiateClaimService{
	@Autowired
	private InitiateClaimRepository initiateClaimRepository;
	@Autowired
	private InsurerDetailRepository insurerDetailRepository;	
	@Autowired
	private IPTreatmentClient ipTreatmentClient;
	@Override
	public double initiateClaim(InitiateClaim claim, String token) throws AuthorizationException {
		log.info("Initiating insurance claim process");
		double total = 0;
		// get insurance details from  claim.getInsurerName()
		double insuranceAmt = insurerDetailRepository.findByInsurerName(claim.getInsurerName()).orElse(new InsurerDetail()).getInsurerPackageAmountLimit();
		// get treatment details from patient name 
		TreatmentPlan treatmentPlan = ipTreatmentClient.getAllTreatmentPlan(token).stream()
				.filter(treatment -> treatment.getPatientDetails().getName().equals(claim.getPatientName()))
				.findFirst().orElse(new TreatmentPlan());
		total = treatmentPlan.getCost() - insuranceAmt <0?0:treatmentPlan.getCost() - insuranceAmt;
		claim.setBalanceAmtToBePaid(total);
		// save initiateClaim 
		initiateClaimRepository.save(claim);
		// update the treatemt status as Completed
		treatmentPlan.setStatus("Completed");
		// update database
		ipTreatmentClient.updateStatus(treatmentPlan, token);
		log.info("Updated treatment status as completed");
		return total;
	}
	
	
	
	

}
