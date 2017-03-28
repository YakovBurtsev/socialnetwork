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
        log.info("get user id={}", id);
        User user = service.get(id);
        log.info("got: {}", user);
        return user;
    }

    User create(User user) {
        log.info("create {}", user);
        user.setId(null);
        return service.save(user);
    }

    void delete(Long id) {
        log.info("delete user id={}", id);
        service.delete(id);
    }

    void update(User user, Long id) {
        user.setId(id);
        log.info("update {}", user);
        service.update(user);
    }

    User getByEmail(String email) {
        log.info("get user by email={}", email);
        return service.getByEmail(email);
    }

    List<UserInfo> findByNameAndSurname(String name, String surname) {
        log.info("find users by name={} and surname={}", name, surname);
        List<UserInfo> userInfoList = service.findByNameAndSurname(name, surname);
        log.info("got: {}", userInfoList);
        return userInfoList;
    }
}
