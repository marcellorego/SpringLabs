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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(callSuper = false, exclude="employee")
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name="SALARY")
@IdClass(SalaryPK.class)
public class Salary extends BaseEntity {

	private static final long serialVersionUID = -1655950353154120106L;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "EMPLOYEE_ID", nullable = false)
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