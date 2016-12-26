package ru.yakovburtsev.socialnetwork.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.yakovburtsev.socialnetwork.core.model.Request;
import ru.yakovburtsev.socialnetwork.core.util.Urls;
import ru.yakovburtsev.socialnetwork.user.auth.AuthorizedUser;

@Controller
@RequestMapping("/request")
public class RequestController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE)
    public String create(@RequestParam(value = "friendId") Long friendId) {
        Request request = new Request(AuthorizedUser.id(), friendId);
        System.out.println(request);
        restTemplate.postForObject(
                Urls.FRIENDS.getUrl().concat("/request"), request, Request.class
        );
        return "friends";
    }

    public boolean isSent(Long toId) {
        return restTemplate.getForObject(
                Urls.FRIENDS.getUrl().concat("/request/isSent?fromId={fromId}&toId={toId}"),
                Boolean.class, AuthorizedUser.id(), toId
        );
    }
}
