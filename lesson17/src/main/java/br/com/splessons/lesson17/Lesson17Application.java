package br.com.splessons.lesson17;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Map;

@SpringBootApplication
@EnableConfigurationProperties(value = { AppProperties.class })
@ComponentScan(basePackageClasses = { JsonProperties.class})
public class Lesson17Application implements CommandLineRunner {

	private static Logger LOGGER = LoggerFactory.getLogger(Lesson17Application.class);

//	@Autowired
//	private JsonProperties jsonProperties;

	@Autowired
	private AppProperties appProperties;

	public static void main(String[] args) {
		SpringApplication.run(Lesson17Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		//LOGGER.info("nome do host {}", jsonProperties.getHost());

		LOGGER.info("property {}", appProperties.getCompiler().getOutputFolder());
	}
}
