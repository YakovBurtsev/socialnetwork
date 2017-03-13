package ru.yakovburtsev.socialnetwork.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;
import ru.yakovburtsev.socialnetwork.core.service.UserService;
import ru.yakovburtsev.socialnetwork.user.util.MessageSender;
import ru.yakovburtsev.socialnetwork.user.util.exception.UserNotFoundException;

import javax.jms.ObjectMessage;
import java.util.ArrayList;

import static ru.yakovburtsev.socialnetwork.user.config.MessagingConfig.USER_QUEUE;
import static ru.yakovburtsev.socialnetwork.user.config.MessagingConfig.USER_RESPONSE_QUEUE;


@Controller
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private static final String SAVE_USER_SELECTOR = "save_user = 'true'";
    private static final String SAVE_USER = "save_user";
    private static final String DELETE_USER_SELECTOR = "delete_user = 'true'";
    private static final String DELETE_USER = "delete_user";
    private static final String GET_BY_EMAIL_SELECTOR = "get_by_email = 'true'";
    private static final String EMAIL = "email";
    private static final String GET_USER_SELECTOR = "get_user = 'true'";
    private static final String GET_USER = "get_user";
    private static final String UPDATE_PROFILE_SELECTOR = "update_profile = 'true'";
    private static final String UPDATE_PROFILE = "update_profile";
    private static final String FIND_USERS_SELECTOR = "find_users = 'true'";
    private static final String FIND_USERS = "find_users";
    private static final String OK = "ok";
    private static final String TRUE = "true";

    private final UserService service;
    private final JmsTemplate jmsTemplate;
    private final MessageSender<User> messageSender;

    @Autowired
    public UserController(UserService service, JmsTemplate jmsTemplate, MessageSender<User> messageSender) {
        this.service = service;
        this.jmsTemplate = jmsTemplate;
        this.messageSender = messageSender;
    }

    @JmsListener(destination = USER_QUEUE, selector = SAVE_USER_SELECTOR)
    public void save(User user) {
        LOGGER.info("save user " + user);
        messageSender.send(service.save(user), SAVE_USER, OK);
    }

    @JmsListener(destination = USER_QUEUE, selector = DELETE_USER_SELECTOR)
    public void delete(Long userId) {
        LOGGER.info("delete user id=" + userId);
        service.delete(userId);
        messageSender.sendTextMessage(DELETE_USER, DELETE_USER, OK);
    }

    @JmsListener(destination = USER_QUEUE, selector = GET_BY_EMAIL_SELECTOR)
    public void getByEmail(String email) {
        LOGGER.info("get user by email=" + email);
        messageSender.send(service.getByEmail(email), EMAIL, email);
    }

    @JmsListener(destination = USER_QUEUE, selector = GET_USER_SELECTOR)
    public void get(Long userId) {
        LOGGER.info("get user id=" + userId);
        try {
            User user = service.get(userId);
            LOGGER.info("got user " + user);
            messageSender.send(user, GET_USER, userId);
        } catch (UserNotFoundException e) {
            LOGGER.info(e.getMessage());
            messageSender.send(null, GET_USER, userId);
        }
    }

    @JmsListener(destination = USER_QUEUE, selector = UPDATE_PROFILE_SELECTOR)
    public void update(User user) {
        LOGGER.info("update user " + user);
        try {
            service.update(user);
            messageSender.sendTextMessage(OK, UPDATE_PROFILE, TRUE);
        } catch (Exception e) {
            messageSender.sendTextMessage(e.getMessage(), UPDATE_PROFILE, TRUE);
        }
    }

    @JmsListener(destination = USER_QUEUE, selector = FIND_USERS_SELECTOR)
    public void findByNameAndSurname(UserInfo userInfo) {
        String name = userInfo.getName();
        String surname = userInfo.getSurname();
        LOGGER.info("find by name={} and surname={}", name, surname);
        jmsTemplate.send(USER_RESPONSE_QUEUE, session -> {
            ObjectMessage objectMessage = session
                    .createObjectMessage(new ArrayList<>(service.findByNameAndSurname(name, surname)));
            objectMessage.setStringProperty(FIND_USERS, OK);
            return objectMessage;
        });
    }
}
