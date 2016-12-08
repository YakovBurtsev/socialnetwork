package ru.yakovburtsev.socialnetwork.user.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yakovburtsev.socialnetwork.core.service.RegistrationService;

/**
 * Created by Yakov Burtsev on 05.12.2016.
 */
public class RegistrationController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RegistrationService service;

    public boolean register(String name, String surname, String email, String password) {
        log.info("register a user with email: " + email);
        return service.register(name, surname, email, password);
    }
}
