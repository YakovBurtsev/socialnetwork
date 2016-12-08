package ru.yakovburtsev.socialnetwork.user.util;

import ru.yakovburtsev.socialnetwork.core.model.User;

/**
 * Created by Yakov Burtsev on 05.12.2016.
 */
public class UserUtil {
    public static User prepareToSave(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
