package com.cts.portal.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cts.portal.exception.AuthorizationException;
import com.cts.portal.feign.IPTreatmentClient;
import com.cts.portal.feign.IPTreatmentOfferingClient;
import com.cts.portal.model.AilmentCategory;
import com.cts.portal.model.IPTreatmentPackage;
import com.cts.portal.model.PackageDetail;
import com.cts.portal.model.SpecialistDetail;

@Controller
@RequestMapping("/portal")
public class TodoStuffController {

	@Autowired
	private IPTreatmentClient ipTreatmentClient;
	@Autowired
	private IPTreatmentOfferingClient ipTreatmentOfferingClient;

	@PostMapping("/searchSpecialistsByExpertise")
	public String getSpecialistsByExpertise(HttpServletRequest request, HttpServletResponse response)
			throws AuthorizationException {
		String jwtToken = (String) request.getSession().getAttribute("Authorization");
		String areaOfExpertise = (String) request.getParameter("areaOfExpertise");

		System.err.println("token : " + jwtToken);
		System.err.println("areaOfExpertise : " + areaOfExpertise);

		ResponseEntity<List<SpecialistDetail>> specialistsByAreaOfExpertise = ipTreatmentOfferingClient
				.getSpecialistsByAreaOfExpertise(areaOfExpertise.toUpperCase(), jwtToken);

		request.setAttribute("list", specialistsByAreaOfExpertise.getBody());

		return "admin-search-specialists-by-specs";
	}

	@PostMapping("/removeSpecialist")
	public String removeSpecialist(HttpServletRequest request, HttpServletResponse response)
			throws AuthorizationException {
		String jwtToken = (String) request.getSession().getAttribute("Authorization");
//		int specialistId = Integer.parseInt(request.getParameter("specialistId"));
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("btn id : " + id);
		ResponseEntity<?> deleteSpecialist = ipTreatmentOfferingClient.deleteSpecialist(jwtToken, id);
		int statusCode = deleteSpecialist.getStatusCodeValue();
//		return getSpecialistsByExpertise(request, response);
		return "admin-welcome-page";
	}

	@PostMapping("/addSpecialist")
	public String addSpecialist(HttpServletRequest request, HttpServletResponse response)
			throws AuthorizationException {
		String jwtToken = (String) request.getSession().getAttribute("Authorization");
		SpecialistDetail specialistDetail = new SpecialistDetail();
		specialistDetail.setAreaOfExpertise(request.getParameter("areaOfExpertise"));
		specialistDetail.setContactNumber(Long.parseLong(request.getParameter("contactNumber")));
		specialistDetail.setName(request.getParameter("name"));
		specialistDetail.setExperienceInYears(Integer.parseInt(request.getParameter("experienceInYears")));
		specialistDetail.setSpecialistId(new Random().nextInt(5000));
		ResponseEntity<?> responseEntity = ipTreatmentOfferingClient.addSpecialist(jwtToken, specialistDetail);
		int statusCode = responseEntity.getStatusCodeValue();
		if (statusCode == 200) {
			System.out.println(statusCode);
		}
		return "admin-welcome-page";
	}

	@PostMapping("/updateTreatmentPackage")
	public String updateTreatmentPackage(HttpServletRequest request, HttpServletResponse response)
			throws AuthorizationException {
		String jwtToken = (String) request.getSession().getAttribute("Authorization");

		IPTreatmentPackage ipTreatmentPackage = new IPTreatmentPackage();

		String ailmentCategory = request.getParameter("ailmentCategory");
		int treatmentPackageId = Integer.parseInt(request.getParameter("treatmentPackageId"));
		int cost = Integer.parseInt(request.getParameter("cost"));
		int pid = Integer.parseInt(request.getParameter("pid"));
		int treatmentDuration = Integer.parseInt(request.getParameter("treatmentDuration"));
		String treatmentPackageName = request.getParameter("treatmentPackageName");

		String[] split = (request.getParameter("testDetails")).split(",");
		split[0] = split[0].replace("[", "");
		split[split.length - 1] = split[split.length - 1].replace("]", "");
		List<String> testDetails = Arrays.stream(split).map(s -> s.trim()).filter(s -> !s.isEmpty())
				.map(s -> s.toUpperCase()).collect(Collectors.toList());

		if (ailmentCategory.equals(AilmentCategory.ORTHOPAIDICS.toString())) {
			ipTreatmentPackage.setAilmentCategory(AilmentCategory.ORTHOPAIDICS);
		} else {
			ipTreatmentPackage.setAilmentCategory(AilmentCategory.UROLOGY);
		}

		ipTreatmentPackage.setTreatmentPackageId(treatmentPackageId);

		PackageDetail detail = new PackageDetail();

		detail.setPid(pid);
		detail.setCost(cost);
		detail.setTestDetails(testDetails);
		detail.setTreatmentPackageName(treatmentPackageName);
		detail.setTreatmentDuration(treatmentDuration);

		ipTreatmentPackage.setPackageDetail(detail);

		ResponseEntity<?> updatePackage = ipTreatmentOfferingClient.updatePackage(jwtToken,
				ipTreatmentPackage.getTreatmentPackageId(), ipTreatmentPackage);

		int statusCode = updatePackage.getStatusCodeValue();

		if (statusCode >= 200 && statusCode < 300) {

		}

		return "admin-welcome-page";
	}

}
