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
		Criteria criteria = getDefaultCriteria("_salary");
		//criteria.createAlias("_salary.employee", "_employee", JoinType.INNER_JOIN );
		//criteria.setFetchMode("employee", FetchMode.JOIN);
		
		/*ProjectionList proList = Projections.projectionList();
        proList.add(Projections.property("employee"), "employee");
        proList.add(Projections.property("fromDate"), "fromDate");
        proList.add(Projections.property("toDate"), "toDate");
        proList.add(Projections.property("salary"), "salary");
        criteria.setProjection(proList);*/
        
        criteria.add(Restrictions.eq("employee", employee));
        criteria.addOrder(Order.asc("fromDate"));
        //criteria.setResultTransformer(new AliasToBeanResultTransformer(Salary.class));
        
        List<Salary> result = (List<Salary>) criteria.list();
        return result ;
	}

	@Override
	public void deleteByEmployee(Employee employee) {
		String hql = "delete from Salary where employee = :employee"; 
		Query qry = getSession().createQuery(hql);
		qry.setLong("employee", employee.getId()).executeUpdate();
	}
}