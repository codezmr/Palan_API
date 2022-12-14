package com.codezmr.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codezmr.constants.AppConstatnts;
import com.codezmr.entity.Plan;
import com.codezmr.props.AppProperties;
import com.codezmr.service.PlanService;

@RestController
public class PlanRestController {

	private Map<String, String> message;
	
	private PlanService planService;

	private AppProperties appProperties;

	public PlanRestController(PlanService planService, AppProperties appProperties) {
		this.planService = planService;
		this.message = appProperties.getMessage();
	}

	@GetMapping("/categories")
	public ResponseEntity<Map<Integer, String>> planCategories() {
		Map<Integer, String> categories = planService.getPlanCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {

		String responseMsg = AppConstatnts.EMPTY_STR ;
		boolean isSaved = planService.savePlan(plan);

		if (isSaved) {
			responseMsg = message.get(AppConstatnts.PLAN_SAVE_SUCC);
		} else {
			responseMsg = message.get(AppConstatnts.PLAN_SAVE_FAIL);
		}

		return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> plans() {

		List<Plan> allPlans = planService.getAllPlans();
		return new ResponseEntity<>(allPlans, HttpStatus.OK);
	}

	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {

		Plan plan = planService.getPlanById(planId);
		return new ResponseEntity<>(plan, HttpStatus.OK);

	}

	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(Plan plan) {
		boolean isUpdated = planService.updatePlan(plan);

		String msg = "";

		if (isUpdated) {

			msg = message.get(AppConstatnts.PLAN_UPDATE_SUCC);
		} else {
			msg = message.get(AppConstatnts.PLAN_UPDATE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<String> deletePlan(@PathVariable Integer planId) {

		boolean isDeleted = planService.deletePlanById(planId);

		String msg = "";

		if (isDeleted) {

			msg = message.get(AppConstatnts.PLAN_DELETE_SUCC);
		} else {
			msg = message.get(AppConstatnts.PLAN_DELETE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status) {

		boolean isStatusChanged = planService.planStatusChange(planId, status);

		String msg = "";

		if (isStatusChanged) {

			msg = message.get(AppConstatnts.PLAN_STATUS_CHANGE);
		} else {
			msg = message.get(AppConstatnts.PLAN_STATUS_CHANGE_FAIL);
		}
		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

}
