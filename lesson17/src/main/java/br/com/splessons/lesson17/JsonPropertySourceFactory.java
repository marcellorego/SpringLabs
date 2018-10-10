package br.com.splessons.lesson17;

import java.io.IOException;
import java.util.Map;

import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

//@Component
public class JsonPropertySourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        Map readValue = new ObjectMapper().readValue(resource.getInputStream(), Map.class);
        //JsonProperties readValue = new ObjectMapper().readValue(resource.getInputStream(), JsonProperties.class);
        return new MapPropertySource("json-property", readValue);
    }
}
