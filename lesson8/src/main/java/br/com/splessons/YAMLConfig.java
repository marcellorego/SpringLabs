package br.com.splessons;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties
public class YAMLConfig {

    private String name;
    private String environment;
    private List<String> servers;
    private MappingConfig mappings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public List<String> getServers() {
        return servers;
    }

    public void setServers(List<String> servers) {
        this.servers = servers;
    }

    public MappingConfig getMappings() {
        return mappings;
    }

    public void setMappings(MappingConfig mappings) {
        this.mappings = mappings;
    }
}