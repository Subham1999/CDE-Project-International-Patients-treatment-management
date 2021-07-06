package com.cts.mfpe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.mfpe.model.PackageDetail;

@Repository
public interface PackageDetailRepository extends JpaRepository<PackageDetail, Integer> {

}
