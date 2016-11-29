package br.com.splessons;

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import br.com.splessons.configuration.ApplicationConfiguration;
import br.com.splessons.model.Employee;
import br.com.splessons.service.IEmployeeService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	final AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    	 
        IEmployeeService service = (IEmployeeService) context.getBean("employeeService");
        
        /*
         * Create Employee1
         */
        Employee employee1 = new Employee();
        employee1.setName("Han Yenn");
        employee1.setJoiningDate(new LocalDate(2010, 10, 10));
        employee1.setSalary(new BigDecimal(10000));
        employee1.setSsn("ssn00000001");
        
        /*
         * Persist both Employees
         */
        service.saveEmployee(employee1);
        
        context.close();
    }
}
