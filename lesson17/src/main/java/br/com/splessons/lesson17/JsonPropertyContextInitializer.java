package br.com.splessons.lesson17;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.Map;

public class JsonPropertyContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {

        try {
            Resource resource = configurableApplicationContext.getResource("classpath:jsonProperties.json");

            //JsonProperties readValue = new ObjectMapper().readValue(resource.getInputStream(), JsonProperties.class);

            Map readValue = new ObjectMapper().readValue(resource.getInputStream(), Map.class);
            //JsonProperties readValue = new ObjectMapper().readValue(resource.getInputStream(), JsonProperties.class);
            PropertySource<?> result = new MapPropertySource("json-property", readValue);

            configurableApplicationContext.getEnvironment().getPropertySources().addFirst(result);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
