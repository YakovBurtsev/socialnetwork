package ru.yakovburtsev.socialnetwork.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;
import ru.yakovburtsev.socialnetwork.user.auth.AuthorizedUser;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/friends")
public class FriendsController {


    @GetMapping
    public String getFriends(ModelMap model) {
//        ResponseEntity<UserInfo[]> responseEntity = restTemplate.getForEntity(
//                Urls.FRIENDS.getUrl().concat("/friends?userId={userId}"), UserInfo[].class, AuthorizedUser.id()
//        );
//        List<UserInfo> friends = Arrays.asList(responseEntity.getBody());
//        model.addAttribute("friends", friends);
        return "friends";
    }

    @DeleteMapping
    public String deleteFromFriends(@RequestParam(value = "friendId") Long friendId) {
//        restTemplate.delete(
//                Urls.FRIENDS.getUrl().concat("/friends?userId={userId}&friendId={friendId}"),
//                AuthorizedUser.id(), friendId
//        );

        return "redirect:";
    }

    public boolean isFriend(Long friendId) {
//        return restTemplate.getForObject(
//                Urls.FRIENDS.getUrl().concat("/friends/isFriend?userId={userId}&friendId={friendId}"),
//                Boolean.class, AuthorizedUser.id(), friendId
//        );
        return false;
    }
}
