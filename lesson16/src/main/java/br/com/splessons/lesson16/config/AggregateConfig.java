package br.com.splessons.lesson16.config;

import com.mongodb.MongoClient;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.mongo.DefaultMongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("br.com.splessons.lesson16.config")
public class AggregateConfig {

    @Autowired
    private MongoClient mongoClient;

    @Bean
    public EventStorageEngine eventStorageEngine() {
        return new MongoEventStorageEngine(new DefaultMongoTemplate(mongoClient));
    }
}
