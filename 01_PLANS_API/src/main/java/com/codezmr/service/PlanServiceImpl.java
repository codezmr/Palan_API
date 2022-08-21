package com.codezmr.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codezmr.entity.Plan;
import com.codezmr.entity.PlanCategory;
import com.codezmr.repo.PlanCategoryRepo;
import com.codezmr.repo.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {
	
	@Autowired
	private PlanRepo planRepo; //Jpa will do the implementation on runtime
	
	@Autowired
	private PlanCategoryRepo planCategoryRepo; //Jpa will do the implementation on runtime
	
	
	@Override
	public Map<Integer, String> getPlanCategories() {
		
	    List<PlanCategory> categories =	planCategoryRepo.findAll();
		
	    Map<Integer, String> categoryMap = new HashMap<>();
	    
	    categories.forEach(category -> {
	    	categoryMap.put(category.getCategoryId(), category.getCategoryName());
	    });
	    
		return categoryMap;
	}

	@Override
	public boolean savePlan(Plan plan) {
		

		 Plan saved = planRepo.save(plan);
		 
		 /* noob style
		  
		 if(saved.getPlanId() != null) {
			 return true;
		 }else {
			 return false;
		 }
		 */
		
		 //1yr experience write like this
		 //return saved.getPlanId() != null ? true : false;
		 
		 return saved.getPlanId() != null; //experienced ppl write like this.
	}

	@Override
	public List<Plan> getAllPlans() {
		
		return planRepo.findAll();
		
	}

	@Override
	public Plan getPlanById(Integer planId) {
		
		
		Optional<Plan> findById = planRepo.findById(planId);
		
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		
		planRepo.save(plan); // save() is upsert method, 
										 //means insert or update-(if the primary key value available then do update)
		
		return plan.getPlanId() != null;
	}

	@Override
	public boolean deletePlanById(Integer planId) {
		
		boolean status = false;
		try {
			planRepo.deleteById(planId);
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
		
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		
		 Optional<Plan> findById = planRepo.findById(planId);
		
		 if(findById.isPresent()) {
			 
			 Plan plan = findById.get();
			 plan.setActiveSw(status);
			 planRepo.save(plan);
			 
			 return true;
		 }
		 
		 
		return false;
	}

}
