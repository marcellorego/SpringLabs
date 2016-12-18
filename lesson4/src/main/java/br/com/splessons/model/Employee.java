package br.com.splessons.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
 
    @Column(name = "NAME", nullable = false)
    private String name;
 
    @Column(name = "JOINING_DATE", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate joiningDate;
 
    @Column(name = "SALARY", nullable = false)
    private BigDecimal salary;
     
    @Column(name = "SSN", unique=true, nullable = false)
    private String ssn;
}