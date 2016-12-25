package ru.yakovburtsev.socialnetwork.user.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.user.auth.AuthorizedUser;

import javax.validation.Valid;

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

    @RequestMapping(value = "/delete")
    public String delete() {
        super.delete(AuthorizedUser.id());
        return "login";
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody User user) {
        user.setId(AuthorizedUser.id());
        super.update(user, user.getId());
    }
}
