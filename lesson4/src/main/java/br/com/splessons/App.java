package br.com.splessons;

import java.math.BigDecimal;

import org.joda.time.LocalDate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
    	final AbstractApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
    	
    	//final AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    	
    	/*final AnnotationConfigApplicationContext  context = new AnnotationConfigApplicationContext();
    	context.scan("br.com.splessons.configuration"); 
    	context.refresh();*/
    	 
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
         * Create Employee2
         */
        Employee employee2 = new Employee();
        employee2.setName("Dan Thomas");
        employee2.setJoiningDate(new LocalDate(2012, 11, 11));
        employee2.setSalary(new BigDecimal(20000));
        employee2.setSsn("ssn00000002");
        
        /*
         * Create Employee3
         */
        Employee employee3 = new Employee();
        employee3.setName("Maria Jose");
        employee3.setJoiningDate(new LocalDate(2012, 11, 11));
        employee3.setSalary(new BigDecimal(20000));
        employee3.setSsn("ssn00000003");
        
        /*
         * Persist both Employees
         */
        service.saveEmployee(employee1);
        service.saveEmployee(employee2);
        service.saveEmployee(employee3);
        
        /*
         * Get all employees list from database
         */
        service.findAllEmployees().forEach(System.out::println);
 
        /*
         * delete an employee
         */
        service.deleteEmployeeBySsn("ssn00000002");
        
        /*
         * update an employee
         */
 
        Employee employee = service.findBySsn("ssn00000001");
        employee.setSalary(new BigDecimal(50000));
        service.updateEmployee(employee);
 
        /*
         * Get all employees list from database
         */
        service.findAllEmployees().stream()
    		.filter(item->"ssn00000003".equals(item.getSsn()))
    		.forEach(System.out::println);
        
        /*
         * delete an employee
         */
        service.deleteEmployeeBySsn("ssn00000001");
        
        /*
         * delete all employees
         */
        service.deleteAll();
        
        context.close();
    }
}
