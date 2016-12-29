package br.com.splessons.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.splessons.dao.IEmployeeDao;
import br.com.splessons.model.Employee;

@Service("employeeService")
public class EmployeeServiceImpl extends BaseImplService<Employee> implements IEmployeeService {
	
    @Autowired
    private IEmployeeDao dao;
    
    @Autowired
	private ISalaryService salaryService;
    
    public void saveEmployee(Employee employee) {
        TransactionStatus status = transactionManager.getTransaction(getDefaultTransactionDefinition());
    	try {
    		dao.saveEmployee(employee);
    		employee.getSalaries().forEach(salary -> salaryService.addSalary(salary));
    		transactionManager.commit(status);
    	} catch (Exception e) {
    		transactionManager.rollback(status);
    	}
    }
 
    @Transactional(readOnly=true, propagation=Propagation.REQUIRED)
    public List<Employee> findAllEmployees() {
        return dao.findAllEmployees();
    }
 
    @Transactional(propagation=Propagation.REQUIRED)
    public void deleteEmployeeBySsn(String ssn) {
    	Employee employee = findBySsn(ssn);
    	if (employee != null) {
    		salaryService.deleteByEmployee(employee);
    		dao.deleteEmployeeBySsn(ssn);
    	}
    }
 
    @Transactional(readOnly=true, propagation=Propagation.REQUIRED)
    public Employee findBySsn(String ssn) {
        return dao.findBySsn(ssn);
    }
 
    @Transactional(propagation=Propagation.REQUIRED)
    public void updateEmployee(Employee employee){
        dao.updateEmployee(employee);
    }
    
    @Transactional(readOnly=true, propagation=Propagation.REQUIRED)
    public Employee findById(Long id) {
        return dao.findById(id);
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public void deleteAll() {
    	
    	findAllEmployees().stream()
    		.forEach(item -> deleteEmployeeBySsn(item.getSsn()));
    }
    
    @Transactional(propagation=Propagation.REQUIRED)
    public List<Employee> addNew(Employee employee) {
    	saveEmployee(employee);
    	return findAllEmployees();
    }
}