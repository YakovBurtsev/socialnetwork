package ru.yakovburtsev.socialnetwork.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.service.UserService;


@Controller
public class UserController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private static final String USER_QUEUE = "user_queue";
    private static final String USER_RESPONSE_QUEUE = "user_response_queue";
    private static final String GET = "get";

    private final UserService userService;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public UserController(UserService userService, JmsTemplate jmsTemplate) {
        this.userService = userService;
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = USER_QUEUE, selector = GET)
    public void get(Long userId) {
        log.info("get " + userId);
        final User user = userService.get(userId);
        jmsTemplate.send(USER_RESPONSE_QUEUE, session -> session.createObjectMessage(user));
    }

//    @GetMapping(value = "/edit")
//    public String updateProfile(ModelMap model) {
//        User user = super.get(AuthorizedUser.id());
//        model.addAttribute("user", user);
//        return "register";
//    }

}
