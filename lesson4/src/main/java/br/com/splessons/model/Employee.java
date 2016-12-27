package br.com.splessons.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.NonFinal;

@Data
@NonFinal
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="EMPLOYEE")
public class Employee extends IdBasedEntity {
 
	private static final long serialVersionUID = 104911046661599665L;

	@Column(name = "NAME", nullable = false)
    private String name;
 
    @Column(name = "JOINING_DATE", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate joiningDate;
    
    @NaturalId
    @Column(name = "SSN", unique=true, nullable = false)
    private String ssn;
    
    @Column(name = "GENDER", nullable = false, length=1)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "employee", cascade=CascadeType.ALL)
    @Transient
    private List<Salary> salaries = new ArrayList<>();
    
    public void addSalary(Salary salary) {
    	this.getSalaries().add(salary);
       	salary.setEmployee(this);
    }
}