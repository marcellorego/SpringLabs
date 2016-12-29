package br.com.splessons.model;

import java.io.Serializable;

import org.joda.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

public class SalaryPK implements Serializable {

	private static final long serialVersionUID = 947904620353747182L;

	@Getter
	@Setter
	private Long employee;

	@Getter
	@Setter
	private LocalDate fromDate;
}