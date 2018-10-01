package br.com.splessons.mongoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

@SpringBootApplication
@EnableEurekaClient
public class MongoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoServiceApplication.class, args);
	}

	@Bean
	public Clock defaultClock() {
		return Clock.systemDefaultZone();
	}
}
