package br.com.splessons.lesson16;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
//@ComponentScan("br.com.splessons.lesson16.config")
public class Lesson16Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson16Application.class, args);
	}
}
