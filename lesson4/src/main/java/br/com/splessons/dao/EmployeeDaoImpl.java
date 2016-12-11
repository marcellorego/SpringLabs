package br.com.splessons.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.splessons.model.Employee;

@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Employee> implements IEmployeeDao {
 
    public void saveEmployee(Employee employee) {
        persist(employee);
    }
 
    @SuppressWarnings("unchecked")
    public List<Employee> findAllEmployees() {
        Criteria criteria = getSession().createCriteria(Employee.class);
        return (List<Employee>) criteria.list();
    }
 
    public void deleteEmployeeBySsn(String ssn) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("delete from ")
    	.append(persistentClass.getSimpleName().toUpperCase())
    	.append(" where ssn = :ssn");
        Query query = getSession().createSQLQuery(sb.toString());
        query.setString("ssn", ssn);
        query.executeUpdate();
    }
 
    public Employee findBySsn(String ssn){
        Criteria criteria = getSession().createCriteria(Employee.class);
        criteria.add(Restrictions.eq("ssn",ssn));
        return (Employee) criteria.uniqueResult();
    }
     
    public void updateEmployee(Employee employee){
        getSession().update(employee);
    }
    
    public Employee findById(Long id){
    	Employee result = (Employee) get(id);
        return result;
    }    
}