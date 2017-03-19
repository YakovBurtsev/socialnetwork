package ru.yakovburtsev.socialnetwork.webclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import ru.yakovburtsev.socialnetwork.core.service.FriendsService;
import ru.yakovburtsev.socialnetwork.core.service.RequestService;
import ru.yakovburtsev.socialnetwork.core.service.UserService;

@Configuration
public class HttpInvokerConfig {
    @Bean
    public HttpInvokerProxyFactoryBean userServiceProxy() {
        HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
        httpInvokerProxyFactoryBean.setServiceUrl("http://localhost:8081/user");
        httpInvokerProxyFactoryBean.setServiceInterface(UserService.class);
        return httpInvokerProxyFactoryBean;
    }

    @Bean
    public HttpInvokerProxyFactoryBean friendsServiceProxy() {
        HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
        httpInvokerProxyFactoryBean.setServiceUrl("http://localhost:8082/friends");
        httpInvokerProxyFactoryBean.setServiceInterface(FriendsService.class);
        return httpInvokerProxyFactoryBean;
    }

    @Bean
    public HttpInvokerProxyFactoryBean requestServiceProxy() {
        HttpInvokerProxyFactoryBean httpInvokerProxyFactoryBean = new HttpInvokerProxyFactoryBean();
        httpInvokerProxyFactoryBean.setServiceUrl("http://localhost:8082/request");
        httpInvokerProxyFactoryBean.setServiceInterface(RequestService.class);
        return httpInvokerProxyFactoryBean;
    }
}
