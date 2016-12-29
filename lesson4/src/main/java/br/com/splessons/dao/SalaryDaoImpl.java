package br.com.splessons.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import br.com.splessons.model.Employee;
import br.com.splessons.model.Salary;

@Repository("salaryDao")
public class SalaryDaoImpl extends AbstractHibernateDao<Salary> implements ISalaryDao {

	@Override
	public void saveSalary(Salary salary) {
		persist(salary);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Salary> findByEmployee(Employee employee) {
		Criteria criteria = getSession().createCriteria(Salary.class);
        criteria.add(Restrictions.eq("employee", employee));
        criteria.addOrder(Order.asc("fromDate"));
        return (List<Salary>) criteria.list();
	}

	@Override
	public void deleteByEmployee(Employee employee) {
		String hql = "delete from Salary where employee = :employee"; 
		Query qry = getSession().createQuery(hql);
		qry.setLong("employee", employee.getId()).executeUpdate();
	}
}