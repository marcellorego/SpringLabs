package br.com.splessons;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.splessons.model.Employee;
import br.com.splessons.model.Gender;
import br.com.splessons.model.Salary;
import br.com.splessons.service.IEmployeeService;
import br.com.splessons.service.ISalaryService;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static final Logger logger = LoggerFactory
			.getLogger(App.class);
	
    public static void main( String[] args )
    {
    	//final AbstractApplicationContext context = new ClassPathXmlApplicationContext("app-config.xml");
    	
    	//final AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    	
    	final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    	context.scan("br.com.splessons.configuration"); 
    	context.refresh();
    	 
        IEmployeeService employeeService = (IEmployeeService) context.getBean("employeeService");
        ISalaryService salaryService = (ISalaryService) context.getBean("salaryService");
        
        /*
         * Create Employee1
         */
        Employee employee1 = new Employee();
        employee1.setName("Han Yenn");
        employee1.setJoiningDate(new LocalDate(2010, 10, 10));
        employee1.setSsn("ssn00000001");
        employee1.setGender(Gender.M);

        Salary salary = Salary
        		.builder()
        		.employee(employee1)
        		.salary(new BigDecimal(10000))
        		.fromDate(new LocalDate(2010, 10, 01))
        		.build();
        
        employee1.addSalary(salary);
        
        /*
         * Create Employee2
         */
        Employee employee2 = new Employee();
        employee2.setName("Dan Thomas");
        employee2.setJoiningDate(new LocalDate(2012, 11, 11));
        employee2.setSsn("ssn00000002");
        employee2.setGender(Gender.M);
        
        /*
         * Create Employee3
         */
        Employee employee3 = new Employee();
        employee3.setName("Maria Jose");
        employee3.setJoiningDate(new LocalDate(2012, 11, 11));
        employee3.setGender(Gender.F);
        employee3.setSsn("ssn00000003");
        
        /*
         * Persist both Employees
         */
        employeeService.saveEmployee(employee1);
        
        Employee employeeFound = employeeService.findBySsn("ssn00000001");
        employeeFound.getSalaries().forEach(item -> logger.info(item.toString()));
        
        employeeService.addNew(employee2).forEach(System.out::println);
        
        employeeService.addNew(employee3).forEach(item -> logger.info(item.toString()));
        
        //Assert.isTrue(!employee3.equals(employee2));
        
        /*
         * Get all employees list from database
         */
        logger.debug("Get all employees list from database");
        List<Employee> result = employeeService.findAllEmployees();
        result.forEach(System.out::println);
 
        /*
         * delete an employee
         */
        employeeService.deleteEmployeeBySsn("ssn00000002");
        
        /*
         * update an employee
         */
        
        final Predicate<Salary> filterSalaryPredicate = e -> e.getSalary().doubleValue() >= 10000;
        
        @SuppressWarnings("unchecked")
		final Consumer<Salary> updateSalaryConsumer = (Consumer<Salary>) context.getBean("salaryConsumer");
        
        Employee employee = employeeService.findBySsn("ssn00000001");
        employee.setSalaries(salaryService.findByEmployee(employee));
        
        employee.getSalaries().stream()
        		.filter(filterSalaryPredicate)
    	    	.forEach(updateSalaryConsumer);
        
        employeeService.updateEmployee(employee);
 
        double sum = employee.getSalaries().stream()
        		.mapToDouble(item -> item.getSalary().doubleValue())
        		.sum();
        logger.info(" ------------------------- Salary sum -------------------------- " + sum);
        
        /*
         * Filter employee from the database list
         */
        employeeService.findAllEmployees().stream()
    		.filter(item->"ssn00000003".equals(item.getSsn()))
    		.forEach(System.out::println);
        
        /*
         * delete an employee
         */
        employeeService.deleteEmployeeBySsn("ssn00000001");
        
        /*
         * delete all employees
         */
        employeeService.deleteAll();
        
        context.close();
        
        System.out.println(" ----------- Finished ----------- ");
    }
}
