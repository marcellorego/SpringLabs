package br.com.splessons.dao;

import java.util.List;

import br.com.splessons.model.Employee;
import br.com.splessons.model.Salary;

public interface ISalaryDao {

	void saveSalary(Salary salary);
	List<Salary> findByEmployee(Employee employee);
	void deleteByEmployee(Employee employee);
}