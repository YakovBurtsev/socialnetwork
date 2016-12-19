package ru.yakovburtsev.socialnetwork.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.yakovburtsev.socialnetwork.core.model.User;

import javax.validation.Valid;

/**
 * The class if root controller where users will register or login.
 */
@Controller
public class RootController {

    @Autowired
    private UserController userController;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegister(@Valid User user, BindingResult result) {
        if (!result.hasErrors()) {
            try {
                userController.create(user);
                return "redirect:login";
            } catch (DataIntegrityViolationException ex) {
                result.addError(new FieldError("user", "email", "Пользователь с таким email уже зарегистрирован"));
            }
        }
        return "register";
    }
}
