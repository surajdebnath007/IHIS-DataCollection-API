package com.ihis.bindings;

import lombok.Data;

@Data
public class Summary {

	private PlanSelectionForm plan;

	private IncomeDtlsForm income;

	private EducationDtlsForm education;

	private CitizenKidsDtls kids;
}
