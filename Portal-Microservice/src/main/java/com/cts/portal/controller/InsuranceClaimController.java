package com.cts.portal.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cts.portal.feign.InsuranceClaimClient;
import com.cts.portal.model.InitiateClaim;
import com.cts.portal.model.InsurerDetail;

@Controller
@RequestMapping("/portal")
public class InsuranceClaimController {

	@Autowired
	private InsuranceClaimClient client;
	
	
	/**
	 * @param request
	 * @return errorPage if token is null
	 * @throws Exception
	 */
	@GetMapping(value = "/getAllInsurerDetail")
	public ModelAndView getAllInsurerDetail(HttpServletRequest request) throws Exception {
		
		if ((String) request.getSession().getAttribute("Authorization") == null) {
			ModelAndView errorPage = new ModelAndView("error-page401");
			return errorPage;
		}
		List<InsurerDetail> insurers = client.getAllInsurers((String) request.getSession().getAttribute("Authorization"));
		ModelAndView model = new ModelAndView("admin-view-insurer-page");
		model.addObject("insurers", insurers);
		return model;
	}
	
	/**
	 * @param initiateClaim
	 * @param patientName
	 * @param ailment
	 * @param request
	 * @return errorPage if token is null
	 * @throws Exception
	 */
	@GetMapping(value = "/markAsComplete/{patientName}/{ailment}")
	public ModelAndView showSelectInsurerPage(@ModelAttribute("initiateClaim") InitiateClaim initiateClaim, @PathVariable String patientName, @PathVariable String ailment, HttpServletRequest request) throws Exception {
		
		if ((String) request.getSession().getAttribute("Authorization") == null) {
			ModelAndView errorPage = new ModelAndView("error-page401");
			return errorPage;
		}
		ModelAndView model = new ModelAndView("admin-select-insurer-page");
		model.addObject("patientName", patientName);
		model.addObject("ailment", ailment);
		return model;
	}
	
	/**
	 * @param request
	 * @return errorPage if token is null
	 * @throws Exception
	 */
	@ModelAttribute("insurersList")
	public List<String> populateInsurerName(HttpServletRequest request) throws Exception {
		return client.getAllInsurers((String) request.getSession().getAttribute("Authorization")).stream().map(InsurerDetail::getInsurerName).collect(Collectors.toList());
	}
	
	/**
	 * @param initiateClaim
	 * @param request
	 * @return errorPage if token is null
	 * @throws Exception
	 */
	@PostMapping(value = "/initiateClaim")
	public ModelAndView initiateClaim(@ModelAttribute("initiateClaim") InitiateClaim initiateClaim, HttpServletRequest request) throws Exception {
		if ((String) request.getSession().getAttribute("Authorization") == null) {
			ModelAndView errorPage = new ModelAndView("error-page401");
			return errorPage;
		}
		double amt = client.initiateClaim(initiateClaim, (String) request.getSession().getAttribute("Authorization"));
		ModelAndView model = new ModelAndView("view-balance-amount-to-be-paid");
		model.addObject("patientName", initiateClaim.getPatientName());
		model.addObject("insurerName", initiateClaim.getInsurerName());
		model.addObject("amt", amt);
		return model;
	}
	
}
