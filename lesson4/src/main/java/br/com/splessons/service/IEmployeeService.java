package br.com.splessons.service;

import java.util.List;

import br.com.splessons.model.Employee;

public interface IEmployeeService {
 
    void saveEmployee(Employee employee);
 
    List<Employee> findAllEmployees();
 
    void deleteEmployeeBySsn(String ssn);
 
    Employee findBySsn(String ssn);
 
    void updateEmployee(Employee employee);
    
    Employee findById(Long id);
    
    void deleteAll();
}