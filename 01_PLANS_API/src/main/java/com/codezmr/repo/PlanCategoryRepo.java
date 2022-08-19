package com.codezmr.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codezmr.entity.PlanCategory;

public interface PlanCategoryRepo extends JpaRepository<PlanCategory, Integer> {
	
}
