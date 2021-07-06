package com.cts.iptreatment.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.iptreatment.exception.PatientNameAlreadyExistsException;
import com.cts.iptreatment.feign.IPTreatmentOfferingClient;
import com.cts.iptreatment.model.AilmentCategory;
import com.cts.iptreatment.model.IPTreatmentPackage;
import com.cts.iptreatment.model.PatientDetails;
import com.cts.iptreatment.model.SpecialistDetail;
import com.cts.iptreatment.model.TreatmentPlan;
import com.cts.iptreatment.repository.PatientDetailsRepository;
import com.cts.iptreatment.repository.TreatmentPlanRepository;

import lombok.extern.slf4j.Slf4j;

@Service @Slf4j
public class PatientDeatilsService {
	@Autowired
	private PatientDetailsRepository patientRepo;
	@Autowired
	private IPTreatmentOfferingClient ipTreatmentOfferingClient;
	@Autowired
	private TreatmentPlanRepository treatment;
	//To save the patient details who registered
	public PatientDetails savePatientDetails(PatientDetails patientDetials) {
		return patientRepo.save(patientDetials);
	}
	//To save the treatment plan of a patient
	public TreatmentPlan saveTreatmentPlan(String token, PatientDetails patientDetials) throws Exception {
		if(checkIfPatientAlreadyRegistered(patientDetials.getName())) {
			throw new PatientNameAlreadyExistsException("Patient is Alreagy Registered for treatment");
		}
		TreatmentPlan treatmentPlan = new TreatmentPlan();
		treatmentPlan.setPackageName(patientDetials.getTretmentPackageName());
		IPTreatmentPackage detail = getPackageByName(token, patientDetials.getAilment(), patientDetials.getTretmentPackageName());
		treatmentPlan.setCost(detail.getPackageDetail().getCost());
		treatmentPlan.setTestDetails(detail.getPackageDetail().getTestDetails());
		LocalDate commencementDate = patientDetials.getTreatmentCommencementDate();
		treatmentPlan.setTreatmentCommenceDate(commencementDate);
		treatmentPlan.setTreatmentEndDate(calculateEndDate(commencementDate,detail.getPackageDetail().getTreatmentDuration()));
		treatmentPlan.setSpecialist(findASpecialist(token, patientDetials.getAilment(), "package 1").getName());
		treatmentPlan.setPatientDetails(patientDetials);
		treatmentPlan.setStatus("In-Progress");
		log.info("TreatmentPlan Detail "+treatmentPlan);
		return treatment.save(treatmentPlan);
	}

	public IPTreatmentPackage getPackageByName(String token, AilmentCategory ailment, String packageName)
			throws Exception {
		return ipTreatmentOfferingClient.getIPTreatmentPackageByName(ailment, packageName, token);
	}

	public LocalDate calculateEndDate(LocalDate today, int week) {
		return today.plus(week, ChronoUnit.WEEKS);
	}

	public SpecialistDetail findASpecialist(String token, AilmentCategory ailment, String packageString) throws Exception {
		List<SpecialistDetail> specialists = ipTreatmentOfferingClient.getAllSpecialist(token).stream()
				.filter(s -> s.getAreaOfExpertise().equals(ailment)).collect(Collectors.toList());
		// When the patient chooses a package 1 then a junior specialist is allocated
		if (packageString.equals("Package 1"))
			return specialists.stream()
					.collect(Collectors.minBy(Comparator.comparingInt(SpecialistDetail::getExperienceInYears))).orElse(new SpecialistDetail());
		// When the patient chooses a package 2 then a senior specialist is allocated
		else
			return specialists.stream()
					.collect(Collectors.maxBy(Comparator.comparingInt(SpecialistDetail::getExperienceInYears))).orElse(new SpecialistDetail());
	}
	// This method returns a list of all treatment plans
	public List<TreatmentPlan> getAllTreatmentPlan() {
		return treatment.findAll();
	}
	//To check if the patient already exists in treatment plans
	public boolean checkIfPatientAlreadyRegistered(String patientName) {
		return getAllTreatmentPlan().stream().anyMatch(t -> t.getPatientDetails().getName().equals(patientName));
	}
	//To update the status of a treatment plan of a patient
	public TreatmentPlan updateStatus(TreatmentPlan treatmentPlan) {
		return treatment.save(treatmentPlan);
	}
	
}
