package com.cts.insurance.claim.service;

import com.cts.insurance.claim.exception.AuthorizationException;
import com.cts.insurance.claim.model.InitiateClaim;

public interface InitiateClaimService {
	public double initiateClaim(InitiateClaim claim, String token) throws AuthorizationException;
}
