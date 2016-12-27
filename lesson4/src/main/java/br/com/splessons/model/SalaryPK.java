package br.com.splessons.model;

import java.io.Serializable;

import org.joda.time.LocalDate;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SalaryPK implements Serializable {

	private static final long serialVersionUID = 947904620353747182L;

	private Long employee;

	private LocalDate fromDate;
}