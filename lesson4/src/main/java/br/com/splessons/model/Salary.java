package br.com.splessons.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude="employee")
@Entity
@Table(name="SALARY")
@IdClass(SalaryPK.class)
public final class Salary {

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Employee employee;
	
	@Id
	@Column(name = "FROM_DATE", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate fromDate;

	@Column(name = "SALARY", nullable = false)
    private BigDecimal salary;
	
	@Column(name = "TO_DATE", nullable = true)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate toDate;
}