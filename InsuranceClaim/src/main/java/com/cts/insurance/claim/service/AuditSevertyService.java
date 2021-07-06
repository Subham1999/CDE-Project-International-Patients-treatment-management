package com.cts.insurance.claim.service;

import java.util.List;

import com.cts.insurance.claim.model.InsurerDetail;

public interface AuditSevertyService {
	List<InsurerDetail> getAllInsurers();
	InsurerDetail addInsurers(InsurerDetail insurer);
	InsurerDetail getInsurerByInsurerPackageName(String insurerPackageName);
}
