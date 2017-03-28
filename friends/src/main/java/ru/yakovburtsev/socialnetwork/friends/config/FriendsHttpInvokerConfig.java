package ru.yakovburtsev.socialnetwork.friends.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import ru.yakovburtsev.socialnetwork.core.service.FriendsService;
import ru.yakovburtsev.socialnetwork.core.service.RequestService;

@Configuration
public class FriendsHttpInvokerConfig {
    private final FriendsService friendsService;
    private final RequestService requestService;

    @Autowired
    public FriendsHttpInvokerConfig(FriendsService friendsService, RequestService requestService) {
        this.friendsService = friendsService;
        this.requestService = requestService;
    }

    @Bean(name = "/friends")
    public HttpInvokerServiceExporter friendsServiceExporter() {
        HttpInvokerServiceExporter serviceExporter = new HttpInvokerServiceExporter();
        serviceExporter.setService(friendsService);
        serviceExporter.setServiceInterface(FriendsService.class);
        return serviceExporter;
    }

    @Bean(name = "/request")
    public HttpInvokerServiceExporter requestServiceExporter() {
        HttpInvokerServiceExporter serviceExporter = new HttpInvokerServiceExporter();
        serviceExporter.setService(requestService);
        serviceExporter.setServiceInterface(RequestService.class);
        return serviceExporter;
    }

}
