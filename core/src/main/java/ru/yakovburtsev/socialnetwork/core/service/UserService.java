package ru.yakovburtsev.socialnetwork.core.service;

import ru.yakovburtsev.socialnetwork.core.model.User;

import java.util.List;

/**
 * The interface defines business logic of managing by objects of {@link User} class.
 */

public interface UserService {
    User save(User user);

    // false if not found
    boolean delete(Long id);

    //null if not found
    User get(Long id);

    //null if not found
    User getByEmail(String email);

    void update(User user);

    List<User> findUser(User lookingFor); //TODO подумать как этот метод еще можно реализовать
}
