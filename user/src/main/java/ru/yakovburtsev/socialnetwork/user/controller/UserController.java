package ru.yakovburtsev.socialnetwork.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.user.auth.AuthorizedUser;

@Controller
public class UserController extends AbstractUserController {

    @GetMapping(value = "/profile")
    public String get(ModelMap model) {
        User user = super.get(AuthorizedUser.id());
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
