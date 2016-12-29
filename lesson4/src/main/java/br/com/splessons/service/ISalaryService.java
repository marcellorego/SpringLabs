package br.com.splessons.service;

import java.util.List;

import br.com.splessons.model.Employee;
import br.com.splessons.model.Salary;

public interface ISalaryService {
	
	void addSalary(Salary salary);
	 
    List<Salary> findByEmployee(Employee employee);
 
    void deleteByEmployee(Employee employee);
}