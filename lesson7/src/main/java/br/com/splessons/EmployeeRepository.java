package br.com.splessons;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List<Employee> findBySalary(BigDecimal salary);
	Optional<Employee> findByssn(String ssn);
}