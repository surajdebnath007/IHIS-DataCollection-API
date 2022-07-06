package com.ihis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ihis.bindings.CitizenKidsDtls;
import com.ihis.bindings.EducationDtlsForm;
import com.ihis.bindings.IncomeDtlsForm;
import com.ihis.bindings.KidsDtlsForm;
import com.ihis.bindings.PlanSelectionForm;
import com.ihis.bindings.Summary;
import com.ihis.entities.AppPlanEntity;
import com.ihis.entities.CitizenChildDtlsEntity;
import com.ihis.entities.CitizenGraduationDtlsEntity;
import com.ihis.entities.CitizenIncomeDtlsEntity;
import com.ihis.entities.CitizenPlansEntity;
import com.ihis.repo.AppPlanRepository;
import com.ihis.repo.CitizenChildDtlsRepository;
import com.ihis.repo.CitizenGraduationDtlsRepository;
import com.ihis.repo.CitizenIncomeDtlsRepository;
import com.ihis.repo.CitizenPlansRepository;
import com.ihis.repo.GraduationYearsRepository;

@Service
public class DCServiceImpl implements DCService {

	@Autowired
	private AppPlanRepository planRepo;

	@Autowired
	private CitizenPlansRepository citizenPlanRepo;

	@Autowired
	private CitizenGraduationDtlsRepository eduRepo;

	@Autowired
	private CitizenIncomeDtlsRepository incomeRepo;

	@Autowired
	private CitizenChildDtlsRepository kidsRepo;

	@Autowired
	private GraduationYearsRepository yearsRepo;

	@Override
	public Map<Integer, String> getPlanNames() {

		List<AppPlanEntity> entityList = planRepo.findAll();
		Map<Integer, String> planMap = new HashMap<>();

		entityList.forEach(entity -> {
			planMap.put(entity.getPlanId(), entity.getPlanName());
		});
		return planMap;
	}

	@Override
	public List<Integer> getGraduationYears() {
		return yearsRepo.getYears();

	}

	@Override
	public String submitPlan(PlanSelectionForm form) {
		CitizenPlansEntity entity = new CitizenPlansEntity();
		BeanUtils.copyProperties(form, entity);
		CitizenPlansEntity savedEntity = citizenPlanRepo.save(entity);

		if (savedEntity == null) {
			return "Plan Selection Failed";
		}
		return "SUCCESS";

	}

	@Override
	public String submitEducationDtls(EducationDtlsForm form) {
		CitizenGraduationDtlsEntity entity = new CitizenGraduationDtlsEntity();
		BeanUtils.copyProperties(form, entity);
		CitizenGraduationDtlsEntity savedEntity = eduRepo.save(entity);

		if (savedEntity == null) {
			return "Plan Selection Failed";
		}
		return "SUCCESS";
	}

	@Override
	public String submitIncomeDtls(IncomeDtlsForm form) {
		CitizenIncomeDtlsEntity entity = new CitizenIncomeDtlsEntity();
		BeanUtils.copyProperties(form, entity);
		CitizenIncomeDtlsEntity savedEntity = incomeRepo.save(entity);

		if (savedEntity == null) {
			return "Plan Selection Failed";
		}
		return "SUCCESS";
	}

	@Override
	public String submitKidsDtls(CitizenKidsDtls citizenKidsDtls) {

		List<KidsDtlsForm> kidsList = citizenKidsDtls.getKids();
		int caseNo = citizenKidsDtls.getCaseNo();

		kidsList.forEach(kid -> {
			CitizenChildDtlsEntity entity = new CitizenChildDtlsEntity();
			BeanUtils.copyProperties(kid, entity);
			entity.setCaseNo(caseNo);
			kidsRepo.save(entity);

		});
		return "SUCCESS";
	}

	@Override
	public Summary getSummary(int caseNo) {

		Summary summary = new Summary();

		// get planselection form
		PlanSelectionForm plan = new PlanSelectionForm();
		CitizenPlansEntity planEntity = citizenPlanRepo.findByCaseNo(caseNo);
		BeanUtils.copyProperties(planEntity, plan);
		summary.setPlan(plan);

		// get incomedtls form
		IncomeDtlsForm income = new IncomeDtlsForm();
		CitizenIncomeDtlsEntity incomeEntity = incomeRepo.findByCaseNo(caseNo);
		BeanUtils.copyProperties(incomeEntity, income);
		summary.setIncome(income);

		// get educationdtlsform
		EducationDtlsForm education = new EducationDtlsForm();
		CitizenGraduationDtlsEntity eduEntity = eduRepo.findByCaseNo(caseNo);
		BeanUtils.copyProperties(eduEntity, education);
		summary.setEducation(education);

		// get kidsdtls form
		List<KidsDtlsForm> kids = new ArrayList<KidsDtlsForm>();
		List<CitizenChildDtlsEntity> kidsEntity = kidsRepo.findByCaseNo(caseNo);

		kidsEntity.forEach(kid -> {
			KidsDtlsForm form = new KidsDtlsForm();
			BeanUtils.copyProperties(kid, form);
			kids.add(form);
		});

		CitizenKidsDtls kidsDtls = new CitizenKidsDtls();
		kidsDtls.setCaseNo(caseNo);
		kidsDtls.setKids(kids);
		summary.setKids(kidsDtls);

		return summary;
	}

}
