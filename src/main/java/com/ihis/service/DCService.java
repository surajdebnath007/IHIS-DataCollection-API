package com.ihis.service;

import java.util.List;
import java.util.Map;

import com.ihis.bindings.CitizenKidsDtls;
import com.ihis.bindings.EducationDtlsForm;
import com.ihis.bindings.IncomeDtlsForm;
import com.ihis.bindings.PlanSelectionForm;
import com.ihis.bindings.Summary;

public interface DCService {

	public Map<Integer, String> getPlanNames();

	public String submitPlan(PlanSelectionForm form);

	public String submitEducationDtls(EducationDtlsForm form);

	public String submitIncomeDtls(IncomeDtlsForm form);

	public String submitKidsDtls(CitizenKidsDtls citizenKidsDtls);

	public List<Integer> getGraduationYears();

	public Summary getSummary(int caseNo);
}
