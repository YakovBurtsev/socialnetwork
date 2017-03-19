package ru.yakovburtsev.socialnetwork.webclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;
import ru.yakovburtsev.socialnetwork.core.service.FriendsService;

import java.util.List;

import static ru.yakovburtsev.socialnetwork.webclient.auth.AuthorizedUser.id;


@Controller
@RequestMapping("/friends")
public class FriendsController {

    private final FriendsService friendsService;

    @Autowired
    public FriendsController(FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @GetMapping
    public String getFriends(ModelMap model) {
        List<UserInfo> friends = friendsService.getFriends(id());
        model.addAttribute("friends", friends);
        return "friends";
    }

    @DeleteMapping
    public void deleteFromFriends(@RequestParam(value = "friendId") Long friendId) {
        friendsService.deleteFromFriends(id(), friendId);
    }

    boolean isFriend(Long friendId) {
        return friendsService.isFriend(id(), friendId);
    }
}
