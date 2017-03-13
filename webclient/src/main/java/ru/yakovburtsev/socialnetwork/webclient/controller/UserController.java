package ru.yakovburtsev.socialnetwork.webclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.webclient.auth.AuthorizedUser;

@Controller
public class UserController extends AbstractUserController {

    @GetMapping(value = "/profile")
    public String get(@RequestParam(value = "userId", required = false) Long userId, ModelMap model) {
        boolean authorized;
        User user;
        if (userId != null) {
            authorized = false;
            user = super.get(userId);
//            friendsMessageReceiver.send(userId, IS_FRIEND, authorizedUserId);
//            if (friendsMessageReceiver.receive(
//                    IS_FRIEND + " = " + authorizedUserId + IS_FRIEND_SELECTOR + userId)) {
//                model.addAttribute("isFriend", true);
//            } else {
//                model.addAttribute("isFriend", false);
//                friendsMessageReceiver.send(userId, IS_SENT, authorizedUserId);
//                if (friendsMessageReceiver.receive(IS_SENT + " = " + authorizedUserId + IS_SENT_SELECTOR)) {
//                    model.addAttribute("isSent", true);
//                } else {
//                    model.addAttribute("isSent", false);
//                }
//            }
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
}
