package com.ihis.bindings;

import java.util.List;

import lombok.Data;

@Data
public class CitizenKidsDtls {

	private int caseNo;

	private List<KidsDtlsForm> kids;
}
