package br.com.splessons;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories
public class MongoConfig {

    private final String defaultDatabase;
    private final String mongoDbUri;

    @Autowired
    public MongoConfig(@Value("${spring.data.mongodb.uri}") String mongoDbUri,
                       @Value("${default.mongo.database}") String defaultDatabase) {
        this.mongoDbUri = mongoDbUri;
        this.defaultDatabase = defaultDatabase;
    }

    @Bean(destroyMethod = "close")
    public MongoClient mongo() {
        return new MongoClient(new MongoClientURI(mongoDbUri));
    }

    @Bean
    public MongoTemplate mongoTemplate(final MongoClient mongo) throws Exception {
        return new MongoTemplate(mongoDbFactory(mongo));
    }

    @Bean
    public MultiTenantMongoDbFactory mongoDbFactory(final MongoClient mongo) throws Exception {
        return new MultiTenantMongoDbFactory(mongo, this.defaultDatabase);
    }
}