package ru.yakovburtsev.socialnetwork.webclient.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;
import ru.yakovburtsev.socialnetwork.core.service.UserService;

import java.util.List;

@Slf4j
abstract class AbstractUserController {

    @Autowired
    private UserService service;

    User get(Long id) {
        return service.get(id);
    }

    User create(User user) {
        user.setId(null);
        return service.save(user);
    }

    void delete(Long id) {
        service.delete(id);
    }

    void update(User user, Long id) {
        user.setId(id);
        service.update(user);
    }

    User getByEmail(String email) {
        return service.getByEmail(email);
    }

    List<UserInfo> findByNameAndSurname(String name, String surname) {
        return service.findByNameAndSurname(name, surname);
    }
}
