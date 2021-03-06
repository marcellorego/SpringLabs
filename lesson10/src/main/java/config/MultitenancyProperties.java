package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties("spring.multitenancy")
public class MultitenancyProperties {

    @NestedConfigurationProperty
    private DataSourceProperties datasource1;

    @NestedConfigurationProperty
    private DataSourceProperties datasource2;

    public DataSourceProperties getDatasource1() {
        return datasource1;
    }

    public void setDatasource1(DataSourceProperties datasource1) {
        this.datasource1 = datasource1;
    }

    public DataSourceProperties getDatasource2() {
        return datasource2;
    }

    public void setDatasource2(DataSourceProperties datasource2) {
        this.datasource2 = datasource2;
    }
}