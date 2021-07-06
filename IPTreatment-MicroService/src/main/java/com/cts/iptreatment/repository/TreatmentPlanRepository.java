package com.cts.iptreatment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.iptreatment.model.TreatmentPlan;

@Repository
public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan,Integer>{

}
