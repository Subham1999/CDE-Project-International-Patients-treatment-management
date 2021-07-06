package com.cts.portal.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.portal.exception.IPTreatmentPackageNotFoundException;
import com.cts.portal.exception.PatientNameAlreadyExistsException;
import com.cts.portal.feign.IPTreatmentClient;
import com.cts.portal.model.AilmentCategory;
import com.cts.portal.model.PatientDetails;
import com.cts.portal.model.TreatmentPlan;

import feign.FeignException;

@Controller
@RequestMapping("/portal")
public class IpTreatmentController {
	
	@Autowired
	private IPTreatmentClient client;

	/**
	 * @param patientDetails
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/treatmentRegister")
	public String showRegisterTreatmentPage(@ModelAttribute("patientDetails") PatientDetails patientDetails, Model model, HttpServletRequest request) throws Exception {
		if ((String) request.getSession().getAttribute("Authorization") == null) {
			return "error-page401";
		}
		System.out.println("================Inside treatment register in Portal++++++++");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		model.addAttribute("date", date);
		if(patientDetails != null && patientDetails.getName()!=null && patientDetails.getTreatmentCommencementDate()!=null) {
			TreatmentPlan plan;
			try {
				
				plan = client.formulateTimetable(patientDetails, (String) request.getSession().getAttribute("Authorization"));
				System.out.println("Hello==========================================================");
				model.addAttribute("plan", plan);	
			} catch (IPTreatmentPackageNotFoundException e) {
				model.addAttribute("error","IP Treatment Package Not Found");
			} catch (PatientNameAlreadyExistsException ex) {
				model.addAttribute("error","Sorry! Patient Name Already Exists");
			} catch (FeignException exx) {
				exx.printStackTrace();
				if(exx.getMessage().contains("Patient is Alreagy"))
					model.addAttribute("error","Sorry! Patient Name Already Exists");
				else
					model.addAttribute("error","Connection exception. Try Again!");
			}
		}
		model.addAttribute("patientDetails", patientDetails);
		return "user-register-treatment-page";
	}
	
	@ModelAttribute("ailmentList")
	public Set<String> populateAilmentEnumList() {
		return EnumSet.allOf(AilmentCategory.class).stream().map(a -> a.name()).collect(Collectors.toSet());

	}

	@ModelAttribute("packageList")
	public List<String> populatePackageList() {
		return Arrays.asList("Package 1", "Package 2");

	}

	/**
	 * @param model
	 * @param request
	 * @return errorPage if token is not set, else give access to the page
	 * @throws Exception
	 */
	@GetMapping(value = "/trackTreatment")
	public String showTrackTreatment(Model model, HttpServletRequest request) throws Exception {
		if ((String) request.getSession().getAttribute("Authorization") == null) {
			return "error-page401";
		}
		model.addAttribute("plans", client.getAllTreatmentPlan((String) request.getSession().getAttribute("Authorization")));
		return "user-track-treatment-page";
	}

}
