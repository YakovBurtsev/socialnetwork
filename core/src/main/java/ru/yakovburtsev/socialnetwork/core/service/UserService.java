package ru.yakovburtsev.socialnetwork.core.service;

import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.util.exception.NotFoundException;

import java.util.List;

/**
 * The interface defines business logic of managing by objects of {@link User} class.
 */

public interface UserService {
    User save(User user);

    void delete(Long id) throws NotFoundException;

    User get(Long id) throws NotFoundException;

    User getByEmail(String email) throws NotFoundException;

    void update(User user);

    List<User> findUser(User lookingFor); //TODO подумать как этот метод еще можно реализовать
}
