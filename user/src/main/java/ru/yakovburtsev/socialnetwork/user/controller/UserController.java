package ru.yakovburtsev.socialnetwork.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import ru.yakovburtsev.socialnetwork.core.service.UserService;

import java.util.ArrayList;
import java.util.List;

import static ru.yakovburtsev.socialnetwork.user.config.MessagingConfig.USER_QUEUE;
import static ru.yakovburtsev.socialnetwork.user.config.MessagingConfig.USER_RESPONSE_QUEUE;

@Controller
public class UserController {
    private final UserService userService;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public UserController(UserService userService, JmsTemplate jmsTemplate) {
        this.userService = userService;
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = USER_QUEUE)
    public void getUsers(List<Long> ids) {
        jmsTemplate.send(USER_RESPONSE_QUEUE, session -> session.createObjectMessage(new ArrayList<>(userService.getUsers(ids))));
    }
}
