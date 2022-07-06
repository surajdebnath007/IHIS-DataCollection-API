package com.ihis.bindings;

import java.time.LocalDate;

import lombok.Data;

@Data
public class KidsDtlsForm {

	private String kidName;

	private LocalDate dob;

	private int zzn;
}
