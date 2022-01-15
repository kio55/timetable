package org.kio55.timetableLab.config;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class DatabaseMigrationsConfig {
    private final DataSource dataSource;

    /**
     * Default constructor
     * @param dataSource input datasource
     */
    public DatabaseMigrationsConfig(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Value("${spring.datasource.location}")
    private String location;

    /**
     * Flyway bean
     * @return flyway
     */
    @Bean
    public Flyway creativesManagementFlyway() {
        Flyway flyway = Flyway
                .configure()
                .dataSource(this.dataSource)
                .table("flyway_schema")
                .baselineOnMigrate(true)
                .locations("db/migration")
                .load();
        try {
            flyway.migrate();
        } catch (FlywayException e) {
            flyway.repair();
            flyway.migrate();
        }

        return flyway;
    }
}
