package br.com.splessons;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
@SequenceGenerator(name="SEQUENCE", sequenceName="SQ_EMPLOYEE", allocationSize = 1)
public class Employee extends IdBasedEntity {
 
    @Column(name = "NAME", nullable = false)
    private String name;
 
    @Column(name = "SALARY", nullable = false)
    private BigDecimal salary;
     
    @Column(name = "SSN", unique=true, nullable = false)
    private String ssn;
}