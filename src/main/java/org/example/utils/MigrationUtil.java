package org.example.utils;

import org.flywaydb.core.Flyway;

public class MigrationUtil {
    public static void migration(){
        Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/DB",
                "postgres",
                "147852")
                .load();

        // Start the migration
        flyway.migrate();
    }
}
