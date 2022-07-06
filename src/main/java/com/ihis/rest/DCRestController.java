package com.ihis.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ihis.bindings.CitizenKidsDtls;
import com.ihis.bindings.EducationDtlsForm;
import com.ihis.bindings.IncomeDtlsForm;
import com.ihis.bindings.PlanSelectionForm;
import com.ihis.bindings.Summary;
import com.ihis.service.DCService;

@RestController
public class DCRestController {

	@Autowired
	private DCService service;

	@GetMapping("/plans")
	public Map<Integer, String> getPlans() {
		return service.getPlanNames();
	}

	@PostMapping("/submitplan")
	public ResponseEntity<String> submitPlan(@RequestBody PlanSelectionForm form) {
		String status = service.submitPlan(form);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

	@PostMapping("/submiteducation")
	public ResponseEntity<String> submitEducationDtls(@RequestBody EducationDtlsForm form) {
		String status = service.submitEducationDtls(form);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

	@PostMapping("/submitincome")
	public ResponseEntity<String> submitIncomeDtls(@RequestBody IncomeDtlsForm form) {
		String status = service.submitIncomeDtls(form);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

	@PostMapping("/submitkids")
	public ResponseEntity<String> submitKidsDtls(CitizenKidsDtls citizenKidsDtls) {
		String status = service.submitKidsDtls(citizenKidsDtls);
		return new ResponseEntity<>(status, HttpStatus.OK);
	}

	@GetMapping("/years")
	public List<Integer> getGraduationYears() {
		return service.getGraduationYears();
	}

	@GetMapping("/summary")
	public Summary getSummary(int caseNo) {
		return service.getSummary(caseNo);
	}
}
