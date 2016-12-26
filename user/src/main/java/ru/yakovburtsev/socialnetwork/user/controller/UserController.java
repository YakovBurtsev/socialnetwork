package ru.yakovburtsev.socialnetwork.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;
import ru.yakovburtsev.socialnetwork.core.util.Urls;
import ru.yakovburtsev.socialnetwork.user.auth.AuthorizedUser;

import java.util.Arrays;
import java.util.List;

@Controller
public class UserController extends AbstractUserController {

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping(value = "/profile")
    public String get(@RequestParam(value = "userId", required = false) Long userId, ModelMap model) {
        boolean authorized;
        User user;
        if (userId != null) {
            authorized = false;
            user = super.get(userId);
        } else {
            authorized = true;
            user = super.get(AuthorizedUser.id());
        }
        model.addAttribute("authorized", authorized);
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping(value = "/edit")
    public String updateProfile(ModelMap model) {
        User user = super.get(AuthorizedUser.id());
        model.addAttribute("user", user);
        return "register";
    }

    @GetMapping(value = "/delete")
    public String delete() {
        super.delete(AuthorizedUser.id());
        return "login";
    }

    @GetMapping(value = "/friends")
    public String getFriends(ModelMap model) {
        ResponseEntity<UserInfo[]> responseEntity = restTemplate.getForEntity(
                Urls.FRIENDS.getUrl().concat("/friends?userId={userId}"), UserInfo[].class, AuthorizedUser.id()
        );
        List<UserInfo> friends = Arrays.asList(responseEntity.getBody());
        model.addAttribute("friends", friends);
        return "friends";
    }
}
