package br.com.splessons.lesson12;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EntityScan(basePackageClasses = {
		Lesson12Application.class,
		Jsr310JpaConverters.class
})
public class Lesson12Application {

	@Value("${spring.jackson.time-zone}")
	private String defaultTimeZone;

	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZone));
	}

	public static void main(String[] args) {
		SpringApplication.run(Lesson12Application.class, args);
	}
}
