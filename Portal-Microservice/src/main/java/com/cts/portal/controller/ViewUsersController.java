package com.cts.portal.controller;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.portal.feign.IPTreatmentClient;
@Controller
@RequestMapping("/portal")
public class ViewUsersController {
	
	@Autowired
	private IPTreatmentClient client;

	/**
	 * @param model
	 * @param request
	 * @return errorPage if token is null
	 * @throws Exception
	 */
	@GetMapping(value = "/adminViewActivePage")
	public String showActivePatientSetails(Model model,  HttpServletRequest request) throws Exception {
		
		if ((String) request.getSession().getAttribute("Authorization") == null) {
			return "error-page401";
		}
		model.addAttribute("plans", client.getAllTreatmentPlan((String) request.getSession().getAttribute("Authorization")).stream().filter(t -> t.getStatus().endsWith("rogress")).collect(Collectors.toList()));
		return "admin-view-active-page";
	}
	
	
	/**
	 * @param model
	 * @param request
	 * @return errorPage if token is null
	 * @throws Exception
	 */
	@GetMapping(value = "/adminViewInactivePage")
	public String showInActivePatientSetails(Model model,  HttpServletRequest request) throws Exception {

		if ((String) request.getSession().getAttribute("Authorization") == null) {
			return "error-page401";
		}
		model.addAttribute("plans", client.getAllTreatmentPlan((String) request.getSession().getAttribute("Authorization")).stream().filter(t -> t.getStatus().startsWith("Completed")).collect(Collectors.toList()));
		return "admin-view-inactive-page";
	}

}
