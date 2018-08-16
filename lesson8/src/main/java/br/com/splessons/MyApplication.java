package br.com.splessons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class MyApplication implements CommandLineRunner {

    @Autowired
    private YAMLConfig myConfig;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MyApplication.class);
        app.run();
    }

    public void run(String... args) throws Exception {
        System.out.println("using environment: " + myConfig.getEnvironment());
        System.out.println("name: " + myConfig.getName());
        System.out.println("servers: " + myConfig.getServers());
        System.out.println("mappings: " + myConfig.getMappings());
    }
}