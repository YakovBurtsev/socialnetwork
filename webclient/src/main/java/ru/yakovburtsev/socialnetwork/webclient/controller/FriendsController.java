/*
package ru.yakovburtsev.socialnetwork.webclient.controller;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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

        log.info("get friends of userId={}", id());
        List<UserInfo> friends = friendsService.getFriends(id());
        log.info("got: {}", friends);
        model.addAttribute("friends", friends);
        return "friends";
    }

    @DeleteMapping
    public void deleteFromFriends(@RequestParam(value = "friendId") Long friendId) {
        log.info("delete friendId={} from friends of userId={}", friendId, id());
        friendsService.deleteFromFriends(id(), friendId);
    }

    boolean isFriend(Long friendId) {
        log.info("delete userId={} from friends userId={}", friendId, id());
        return friendsService.isFriend(id(), friendId);
    }
}
*/
