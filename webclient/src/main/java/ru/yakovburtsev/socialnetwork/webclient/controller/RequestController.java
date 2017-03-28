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
        requestService.create(new Request(id(), friendId));
    }

    boolean isSent(Long toId) {
        return requestService.isSent(id(), toId);
    }
}
