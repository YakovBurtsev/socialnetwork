package ru.yakovburtsev.socialnetwork.webclient.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.yakovburtsev.socialnetwork.core.model.Role;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.webclient.auth.AuthorizedUser;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.EnumSet;

import static ru.yakovburtsev.socialnetwork.webclient.util.ImageUtil.saveImage;
import static ru.yakovburtsev.socialnetwork.webclient.util.ImageUtil.validateImage;

/**
 * The class if root controller where users will register or login.
 */
@Controller
public class RootController extends AbstractUserController {

    @GetMapping(value = {"/", "/login"})
    public String login() {
        return "login";
    }

    @GetMapping(value = "/register")
    public String register(ModelMap model) {
        model.addAttribute("user", new User());
        model.addAttribute("register", true);
        return "register";
    }

    @PostMapping(value = "/register")
    public String saveRegister(@Valid User user, BindingResult result, HttpServletRequest request,
                               @RequestParam(value = "avatar", required = false) MultipartFile avatar) {
        if (!result.hasErrors()) {
            try {
                user.setRoles(EnumSet.of(Role.ROLE_USER));
                super.create(user);

                if (!avatar.isEmpty()) {
                    validateImage(avatar);
                    String filename = request
                            .getSession()
                            .getServletContext()
                            .getRealPath("/") + "/resources/images/" + user.getId().toString() + ".jpg";
                    saveImage(filename, avatar);
                }

                return "redirect:login";
            } catch (DataIntegrityViolationException e) {
                result.addError(new FieldError("user", "email", "Пользователь с таким email уже зарегистрирован"));
            }
        }
        return "register";
    }

    @PostMapping(value = "/edit")
    public String updateProfile(@Valid User user, BindingResult result) {
        if (!result.hasErrors()) {
            try {
                user.setId(AuthorizedUser.id());
                super.update(user, user.getId());
                AuthorizedUser.get().update(user);
                return "redirect:profile";
            } catch (DataIntegrityViolationException e) {
                result.addError(new FieldError("user", "email", "Пользователь с таким email уже зарегистрирован"));
            }
        }
        return "register";
    }
}
