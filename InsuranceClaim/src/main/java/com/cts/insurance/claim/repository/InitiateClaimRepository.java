package com.cts.insurance.claim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.insurance.claim.model.InitiateClaim;
@Repository
public interface InitiateClaimRepository extends JpaRepository<InitiateClaim, Integer> {

}
