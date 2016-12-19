package ru.yakovburtsev.socialnetwork.user.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.yakovburtsev.socialnetwork.core.model.User;

import javax.validation.Valid;

/**
 * The class if root controller where users will register or login.
 */
@Controller
public class RootController {

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String saveRegister(@Valid User user, BindingResult result, ModelMap model) {
        if (!result.hasErrors()) {
            try {
                return "redirect:login";
            } catch (DataIntegrityViolationException ex) {
                result.rejectValue("email", "duplicate email");
            }
        }
        return "register";
    }
}
