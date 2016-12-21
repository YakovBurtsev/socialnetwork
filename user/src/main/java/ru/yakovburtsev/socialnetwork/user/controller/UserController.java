package ru.yakovburtsev.socialnetwork.user.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.user.auth.AuthorizedUser;

import javax.validation.Valid;

@RestController
public class UserController extends AbstractUserController {
    static final String REST_URL = "/profile";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return super.get(AuthorizedUser.id());
    }

    @DeleteMapping
    public void delete() {
        super.delete(AuthorizedUser.id());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody User user) {
        user.setId(AuthorizedUser.id());
        super.update(user, user.getId());
    }
}
