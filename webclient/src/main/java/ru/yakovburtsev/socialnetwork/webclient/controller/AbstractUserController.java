package ru.yakovburtsev.socialnetwork.webclient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;
import ru.yakovburtsev.socialnetwork.webclient.util.MessageReceiver;
import ru.yakovburtsev.socialnetwork.webclient.util.MessageSender;

import java.util.List;

abstract class AbstractUserController {
    private static final String TRUE = "true";
    private static final String GET_USER = "get_user";
    private static final String GET_USER_SELECTOR = "get_user = ";
    private static final String SAVE_USER = "save_user";
    private static final String SAVE_USER_SELECTOR = "save_user = 'ok'";
    private static final String DELETE_USER = "delete_user";
    private static final String DELETE_USER_SELECTOR = "delete_user = 'ok'";
    private static final String UPDATE_PROFILE = "update_profile";
    private static final String UPDATE_PROFILE_SELECTOR = "update_profile = 'ok'";
    private static final String GET_BY_EMAIL = "get_by_email";
    private static final String GET_BY_EMAIL_SELECTOR = "get_by_email = ";
    private static final String FIND_USERS = "find_users";
    private static final String FIND_USERS_SELECTOR = "find_users = 'ok'";

    final Logger log = LoggerFactory.getLogger(getClass());

    private static String quoted(Object obj) {
        return "'" + obj + "'";
    }

    @Autowired
    private MessageReceiver<User> userMessageReceiver;

    @Autowired
    private MessageSender<User> userMessageSender;

    @Autowired
    private MessageReceiver<UserInfo> userInfoMessageReceiver;

    @Autowired
    private MessageSender<UserInfo> userInfoMessageSender;

    User get(Long id) {
        log.info("get user id=" + id);
        userMessageSender.sendId(id, GET_USER, TRUE);
        User user = userMessageReceiver.receive(GET_USER_SELECTOR + quoted(id));
        log.info("got user " + user);
        return user;
    }

    User create(User user) {
        user.setId(null);
        log.info("create user " + user);
        userMessageSender.send(user, SAVE_USER, TRUE);
        return userMessageReceiver.receive(SAVE_USER_SELECTOR);
    }

    void delete(Long id) {
        log.info("delete user id=" + id);
        userMessageSender.sendId(id, DELETE_USER, TRUE);
    }

    void update(User user, Long id) {
        user.setId(id);
        log.info("update profile" + user);
        userMessageSender.send(user, UPDATE_PROFILE, TRUE);
    }

    User getByEmail(String email) {
        log.info("getByEmail " + email);
        userMessageSender.sendTextMessage(email, GET_BY_EMAIL, email);
        return userMessageReceiver.receive(GET_BY_EMAIL_SELECTOR + quoted(email));
    }

    List<UserInfo> findByNameAndSurname(String name, String surname) {
        log.info("find by name={} and surname={}", name, surname);
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setSurname(surname);
        userInfoMessageSender.send(userInfo, FIND_USERS, TRUE);
        return userInfoMessageReceiver
                .receiveList(FIND_USERS_SELECTOR + quoted(name + " " + surname));
    }
}
