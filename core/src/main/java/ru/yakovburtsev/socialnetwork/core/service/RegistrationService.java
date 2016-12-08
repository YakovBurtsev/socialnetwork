package ru.yakovburtsev.socialnetwork.core.service;

import ru.yakovburtsev.socialnetwork.core.util.exception.RegistrationException;

/**
 * The interface of registration a user in the social network.
 */
public interface RegistrationService {

    /**
     * Register a user in the social network.
     *
     * @param name must be checked in frontend
     * @param surname must be checked in frontend
     * @param email must be checked in frontend
     * @param password must be checked in frontend
     * @return {@code true} if a registration is successful
     * @throws RegistrationException if user with such email is already registered
     */
    boolean register(String name, String surname, String email, String password) throws RegistrationException;
}
