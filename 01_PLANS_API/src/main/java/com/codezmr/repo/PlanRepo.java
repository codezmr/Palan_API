package com.codezmr.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codezmr.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Integer> {

}
