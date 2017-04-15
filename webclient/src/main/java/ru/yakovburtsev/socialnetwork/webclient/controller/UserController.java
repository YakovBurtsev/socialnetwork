/*
package ru.yakovburtsev.socialnetwork.webclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static ru.yakovburtsev.socialnetwork.webclient.auth.AuthorizedUser.id;

@Controller
public class UserController extends AbstractUserController {
    private final FriendsController friendsController;
    private final RequestController requestController;

    @Autowired
    public UserController(FriendsController friendsController, RequestController requestController) {
        this.friendsController = friendsController;
        this.requestController = requestController;
    }

    @GetMapping(value = "/profile")
    public String get(@RequestParam(value = "userId", required = false) Long userId, ModelMap model) {
        model
                .addAttribute("authorized", userId == null)
                .addAttribute("user", userId != null ? super.get(userId) : super.get(id()))
                .addAttribute("isFriend", userId != null && friendsController.isFriend(userId))
                .addAttribute("isSent", userId != null && requestController.isSent(userId));
        return "profile";
    }

    @GetMapping(value = "/edit")
    public String updateProfile(ModelMap model) {
        model.addAttribute("user", super.get(id()));
        return "register";
    }
}
*/
