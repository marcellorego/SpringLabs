package br.com.splessons.simpleservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SimpleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleServiceApplication.class, args);
	}
}