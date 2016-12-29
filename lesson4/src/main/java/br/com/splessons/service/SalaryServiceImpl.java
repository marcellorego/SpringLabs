package br.com.splessons.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.splessons.dao.ISalaryDao;
import br.com.splessons.model.Employee;
import br.com.splessons.model.Salary;

@Service("salaryService")
public class SalaryServiceImpl implements ISalaryService {

	@Autowired
    private ISalaryDao dao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void addSalary(Salary salary) {
		dao.saveSalary(salary);
	}

	@Override
	@Transactional(readOnly=true, propagation=Propagation.REQUIRED)
	public List<Salary> findByEmployee(Employee employee) {
		return dao.findByEmployee(employee);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteByEmployee(Employee employee) {
		dao.deleteByEmployee(employee);
	}

}
