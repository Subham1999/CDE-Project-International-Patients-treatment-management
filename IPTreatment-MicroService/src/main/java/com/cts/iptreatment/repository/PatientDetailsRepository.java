package com.cts.iptreatment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.iptreatment.model.PatientDetails;


@Repository
public interface PatientDetailsRepository extends JpaRepository<PatientDetails, String> {

}
