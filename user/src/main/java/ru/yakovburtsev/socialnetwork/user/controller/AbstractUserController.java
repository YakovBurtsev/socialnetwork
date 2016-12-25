package ru.yakovburtsev.socialnetwork.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;
import ru.yakovburtsev.socialnetwork.core.service.UserService;

import java.util.List;

abstract class AbstractUserController {
    final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public User get(Long id) {
        log.info("get " + id);
        return service.get(id);
    }

    public User create(User user) {
        user.setId(null);
        log.info("create " + user);
        return service.save(user);
    }

    public void delete(Long id) {
        log.info("delete " + id);
        service.delete(id);
    }

    public void update(User user, Long id) {
        user.setId(id);
        log.info("update " + user);
        service.update(user);
    }

    public User getByEmail(String email) {
        log.info("getByEmail " + email);
        return service.getByEmail(email);
    }

    public List<UserInfo> findByNameAndSurname(String name, String surname) {
        log.info("find by name and surname");
        return service.findByNameAndSurname(name, surname);
    }
}
