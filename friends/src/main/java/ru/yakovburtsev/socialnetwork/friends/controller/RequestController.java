package ru.yakovburtsev.socialnetwork.friends.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.yakovburtsev.socialnetwork.core.model.Request;
import ru.yakovburtsev.socialnetwork.core.service.RequestService;

import java.util.List;

@RestController
@RequestMapping(value = RequestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RequestController {
    static final String REST_URL = "/request";
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestController.class);

    @Autowired
    private RequestService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Request create(@RequestBody Request request) {
        LOGGER.info("create request {}", request);
        return service.create(request);
    }

    @DeleteMapping
    public void delete(@RequestParam(value = "id") Long id) {
        LOGGER.info("delete request {}", id);
        service.delete(id);
    }

    @GetMapping("/sent")
    public List<Request> getSentRequests(@RequestParam Long userId) {
        return service.getSentRequests(userId);
    }
    @GetMapping("/received")
    public List<Request> getReceivedRequests(@RequestParam Long userId) {
        return service.getReceivedRequests(userId);
    }

    @GetMapping(value = "/isSent")
    public boolean isSent(
            @RequestParam(value = "fromId") Long fromId,
            @RequestParam(value = "toId") Long toId
    ) {
        LOGGER.info("is request sent from {} to {}", fromId, toId);
        return service.isSent(fromId, toId);
    }
}
