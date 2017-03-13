package ru.yakovburtsev.socialnetwork.webclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.service.UserService;
import ru.yakovburtsev.socialnetwork.webclient.auth.AuthorizedUser;

import javax.jms.TextMessage;

import static ru.yakovburtsev.socialnetwork.webclient.config.MessagingConfig.USER_RESPONSE_QUEUE;

/**
 * This class is implementation of {@link UserService} and @{@link UserDetailsService} interfaces.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final JmsTemplate jmsTemplate;

    @Autowired
    public UserDetailsServiceImpl(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        jmsTemplate.send((session -> {
            TextMessage textMessage = session.createTextMessage(email);
            textMessage.setStringProperty("get_by_email", "true");
            return textMessage;
        }));
        User user = (User) jmsTemplate.receiveSelectedAndConvert(USER_RESPONSE_QUEUE, "email = " + "'" + email + "'");
        return new AuthorizedUser(user);
    }
}
