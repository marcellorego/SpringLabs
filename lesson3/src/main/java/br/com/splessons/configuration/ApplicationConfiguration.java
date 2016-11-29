package br.com.splessons.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("br.com.splessons.domain.impl")
public class ApplicationConfiguration {	
}