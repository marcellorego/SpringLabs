package br.com.splessons.dao;

import java.util.List;

import br.com.splessons.model.Employee;
  
public interface IEmployeeDao extends IAbstractDao<Employee> {
 
    void saveEmployee(Employee employee);
     
    List<Employee> findAllEmployees();
     
    void deleteEmployeeBySsn(String ssn);
     
    Employee findBySsn(String ssn);
     
    void updateEmployee(Employee employee);
}