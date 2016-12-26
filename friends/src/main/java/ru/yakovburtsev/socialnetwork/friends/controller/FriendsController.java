package ru.yakovburtsev.socialnetwork.friends.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;
import ru.yakovburtsev.socialnetwork.core.service.FriendsService;

import java.util.List;

@RestController
@RequestMapping(value = FriendsController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class FriendsController {
    static final String REST_URL = "/friends";
    private static final Logger LOGGER = LoggerFactory.getLogger(FriendsController.class);

    @Autowired
    private FriendsService service;

    @GetMapping
    public List<UserInfo> getFriends(@RequestParam(value = "userId") Long userId) {
        LOGGER.info("getFriends for userId={}", userId);
        return service.getFriends(userId);
    }

    @DeleteMapping
    public void delete(
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "friendId") Long friendId
    ) {
        LOGGER.info("delete from friends userId={}, friendId={}", userId, friendId);
        service.deleteFromFriends(userId, friendId);
    }

    @PostMapping
    public void addFriend(
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "friendId") Long friendId
    ) {
        LOGGER.info("add friend userId={}, friendId={}", userId, friendId);
        service.addFriend(userId, friendId);
    }

    @GetMapping(value = "/isFriend")
    public boolean isFriend(
            @RequestParam(value = "userId") Long userId,
            @RequestParam(value = "friendId") Long friendId
    ) {
        LOGGER.info("is user {} friend of {}", userId, friendId);
        return service.isFriend(userId, friendId);
    }
}
