package br.com.splessons.lesson17;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

//@Configuration
public class JsonPropertyConfig {

    @Value("classpath:jsonProperties.json")
    private Resource resourceFile;

    //@Bean
    public JsonProperties jsonProperties() {

        try {
            JsonProperties readValue = new ObjectMapper().readValue(resourceFile.getInputStream(), JsonProperties.class);
            return readValue;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
