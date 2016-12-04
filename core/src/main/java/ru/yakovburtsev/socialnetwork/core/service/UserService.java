package ru.yakovburtsev.socialnetwork.core.service;

import ru.yakovburtsev.socialnetwork.core.model.User;

import java.util.List;

/**
 * The interface defines business logic of managing by objects of {@link User} class.
 */

public interface UserService {
    void create(User user);

    User get(Long id);

    void update(User user);

    void delete(Long id);

    List<User> findUser(User lookingFor);
}
