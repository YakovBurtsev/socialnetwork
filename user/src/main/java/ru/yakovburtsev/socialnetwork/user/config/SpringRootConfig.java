package ru.yakovburtsev.socialnetwork.user.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@EnableTransactionManagement
@ComponentScan("ru.yakovburtsev.socialnetwork.user")
public class SpringRootConfig {
    @Autowired
    DataSource dataSource;

    @Bean
    PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource);
    }
}
