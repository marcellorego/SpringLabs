package br.com.splessons;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

public class MultiTenantMongoDbFactory extends SimpleMongoDbFactory {

    private static final Logger logger = LoggerFactory.getLogger(MultiTenantMongoDbFactory.class);

    private final String defaultDatabaseName;

    public MultiTenantMongoDbFactory(final MongoClient mongo, final String defaultDatabaseName) {
        super(mongo, defaultDatabaseName);
        logger.debug("Instantiating " + MultiTenantMongoDbFactory.class.getName() + " with default database name: " + defaultDatabaseName);
        this.defaultDatabaseName = defaultDatabaseName;
    }

    @Override
    public DB getDb() {
        final String dbToUse = TenantContext.get(defaultDatabaseName);
        logger.debug("Acquiring database: " + dbToUse);
        return super.getDb(dbToUse);
    }
}
