package ru.yakovburtsev.socialnetwork.user.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class SpringDbConfig {
    @Bean
    public DataSource dataSource() {
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("initDb_H2.sql")
                .addScript("populateDb_H2.sql")
                .build();
    }

    // Start WebServer, access http://localhost:8082
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server startDBManager() throws SQLException {
        return Server.createWebServer();
    }
}
