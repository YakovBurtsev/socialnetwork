package ru.yakovburtsev.socialnetwork.user.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
@ComponentScan("ru.yakovburtsev.socialnetwork.user")
public class SpringRootConfig {
    @Autowired
    DataSource dataSource;
}
