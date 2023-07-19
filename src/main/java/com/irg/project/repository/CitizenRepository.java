package com.irg.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.irg.project.entity.CitizenPlan;

public interface CitizenRepository extends JpaRepository<CitizenPlan, Integer>{
	
	@Query("select distinct(planName) from CitizenPlan")
	public List<String> getPlanName();
	
	@Query("select distinct(planStatus) from CitizenPlan")
	public List<String> getPlanStatus();

}
