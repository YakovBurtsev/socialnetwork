package ru.yakovburtsev.socialnetwork.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import ru.yakovburtsev.socialnetwork.core.service.UserService;

@Configuration
public class HttpInvokerConfig  {
    private final UserService userService;

    @Autowired
    public HttpInvokerConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean(name = "/user")
    public HttpInvokerServiceExporter userServiceExporter() {
        HttpInvokerServiceExporter serviceExporter = new HttpInvokerServiceExporter();
        serviceExporter.setService(userService);
        serviceExporter.setServiceInterface(UserService.class);
        return serviceExporter;
    }
}
