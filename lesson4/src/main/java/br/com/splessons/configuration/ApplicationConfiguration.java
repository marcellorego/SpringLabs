package br.com.splessons.configuration;

import java.math.BigDecimal;
import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.splessons.model.Salary;

@Configuration
@ComponentScan(basePackages = "br.com.splessons")
public class ApplicationConfiguration {

	public BigDecimal getSalaryFactor() {
		return BigDecimal.valueOf(1.1);
	}
	
	@Bean(name="salaryConsumer")
	public Consumer<Salary> getSalaryConsumer() {
		return e -> e.setSalary(e.getSalary().multiply(getSalaryFactor()));
	}
}