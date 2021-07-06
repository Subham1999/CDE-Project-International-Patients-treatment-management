package com.cts.insurance.claim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.insurance.claim.model.InsurerDetail;

@Repository
public interface InsurerDetailRepository extends JpaRepository<InsurerDetail, Integer> {
	@Query("Select i from InsurerDetail i where i.insurerPackageName = ?1")
	Optional<InsurerDetail> findByName(String insurerPackageName);
	@Query("Select i from InsurerDetail i where i.insurerName = ?1")
	Optional<InsurerDetail> findByInsurerName(String insurerName);
}
