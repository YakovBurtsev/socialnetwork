package ru.yakovburtsev.socialnetwork.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.yakovburtsev.socialnetwork.core.model.Role;
import ru.yakovburtsev.socialnetwork.core.model.User;

import javax.validation.Valid;
import java.util.EnumSet;

/**
 * The class if root controller where users will register or login.
 */
@Controller
public class RootController {

    @Autowired
    private AdminController adminController;

    @GetMapping(value = {"/", "/login"})
    public String login(ModelMap model) {
        return "login";
    }

    @GetMapping(value = "/register")
    public String register(ModelMap model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping(value = "/register")
    public String saveRegister(@Valid User user, BindingResult result) {
        if (!result.hasErrors()) {
            try {
                user.setRoles(EnumSet.of(Role.ROLE_USER));
                adminController.create(user);
                return "redirect:login";
            } catch (DataIntegrityViolationException e) {
                result.addError(new FieldError("user", "email", "Пользователь с таким email уже зарегистрирован"));
            }
        }
        return "register";
    }
}
