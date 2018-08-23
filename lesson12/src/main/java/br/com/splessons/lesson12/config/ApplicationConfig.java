package br.com.splessons.lesson12.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ApplicationConfig {

    @Bean
    public Clock appClock() {
        return Clock.systemDefaultZone();
    }

}
