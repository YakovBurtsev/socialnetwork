package ru.yakovburtsev.socialnetwork.friends.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;


@Configuration
@Import({
        FriendsConfig.class,
        MessagingConfig.class
})
public class TestConfig {

    @Bean
    public DataSource dataSource() {
        // no need shutdown, EmbeddedDatabaseFactoryBean will take care of this
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("initUsersDb_H2.sql")
                .addScript("populateUsersDb_H2.sql")
                .addScript("initFriendsDb_H2.sql")
                .addScript("populateFriendsDb_H2.sql")
                .addScript("initRequestsDb_H2.sql")
                .addScript("populateRequestsDb_H2.sql")
                .build();
    }

    // Start WebServer, access http://localhost:8082
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server startDBManager() throws SQLException {
        return Server.createWebServer();
    }

}
