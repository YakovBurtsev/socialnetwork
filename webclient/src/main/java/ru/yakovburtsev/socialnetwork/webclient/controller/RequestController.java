package ru.yakovburtsev.socialnetwork.webclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yakovburtsev.socialnetwork.core.model.Request;
import ru.yakovburtsev.socialnetwork.core.service.RequestService;

import static ru.yakovburtsev.socialnetwork.webclient.auth.AuthorizedUser.id;

@Slf4j
@Controller
@RequestMapping("/request")
public class RequestController {
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping(value = "/send")
    public void create(@RequestParam(value = "friendId") Long friendId) {
        Request request = new Request(id(), friendId);
        log.info("create {}", request);
        requestService.create(request);
    }

    boolean isSent(Long toId) {
        boolean isSent = requestService.isSent(id(), toId);
        log.info("check whether userId={} sent add to friend-list request to userId={}", id(), toId);
        return isSent;
    }
}
