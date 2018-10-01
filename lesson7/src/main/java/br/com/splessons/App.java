package br.com.splessons;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class App implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(App.class);
	
	private static final AtomicLong counter = new AtomicLong();

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	/*@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			saveEmployees();
			
			findAll();
		
			logBySsn("1");
		
			logBySsn("2");
		};
	}*/
	
	@Autowired
	private EmployeeRepository repository;
	
	@Override
	public void run(String... args) throws Exception {
		
		saveEmployees();
		
		findAll();
		
		logEmployeeBySsn("0");
		
		logEmployeeBySsn("1");
		
		logEmployeeBySsn("2");
		
		logEmployeeBySalary(BigDecimal.valueOf(1000));
	}
	
	@SneakyThrows
	private void saveEmployees() {
		// save a couple of employees
		repository.save(new Employee("Jack", BigDecimal.valueOf(1000), Long.toString(counter.getAndIncrement())));
		repository.save(new Employee("Bauer", BigDecimal.valueOf(2000), Long.toString(counter.getAndIncrement())));
	}
	
	@SneakyThrows
	private void findAll() {
		// fetch all employees
		log.info("Employee found with findAll():");
		log.info("-------------------------------");
		repository.findAll().forEach(e -> log.info(e.toString()));
		log.info("");
	}
	
	@SneakyThrows
	private void logEmployeeBySsn(String ssn) {
		// fetch employees by ssn
		log.info("Employee found with findByssn(ssn):");
		log.info("--------------------------------------------");
		Optional<Employee> employee = repository.findByssn(ssn);
		employee
			.ifPresent(e -> log.info(e.toString()))
			;
		log.info("");
	}
	
	private void logEmployeeBySalary(BigDecimal salary) {
		// fetch employees by ssn
		log.info("Employee found with findBySalary(1000):");
		log.info("--------------------------------------------");
		repository.findBySalary(salary)
			.stream()
			.map(s -> s.toString())
		    .forEach(log::info);
		log.info("");
	}
}
