package ru.yakovburtsev.socialnetwork.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.user.auth.AuthorizedUser;

@Controller
public class UserController extends AbstractUserController {

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
}
