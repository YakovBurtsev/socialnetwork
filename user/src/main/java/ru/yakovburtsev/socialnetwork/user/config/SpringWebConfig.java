package ru.yakovburtsev.socialnetwork.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * This configuration class can be treated as a replacement of spring-servlet.xml
 * as it contains all the information required for component-scanning and view resolver.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"ru.yakovburtsev.socialnetwork.user.controller"})
public class SpringWebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // all resources inside folder src/main/webapp/resources are mapped so they can be referred to inside JSP files
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
        // uses WebJars so Javascript and CSS libs can be declared as Maven dependencies (Bootstrap, jQuery...)
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);

        return resolver;
    }
}
