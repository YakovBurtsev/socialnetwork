package ru.yakovburtsev.socialnetwork.webclient.util;

import ru.yakovburtsev.socialnetwork.core.model.User;

/**
 * The class contains util methods for managing by instances of {@link User} class
 */
public class UserUtil {
    private UserUtil() {
    }

    public static User prepareToSave(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
