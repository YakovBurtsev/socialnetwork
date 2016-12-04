package ru.yakovburtsev.socialnetwork.core.service;

import ru.yakovburtsev.socialnetwork.core.model.User;

/**
 * The interface of registration a user in the social network.
 */
public interface RegistrationService {
    boolean register(User user);
}
