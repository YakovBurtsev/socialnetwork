package ru.yakovburtsev.socialnetwork.user.repository;


import ru.yakovburtsev.socialnetwork.core.model.User;

import java.util.List;

/**
 * The interface define data access objects methods for instances of
 * {@link ru.yakovburtsev.socialnetwork.core.model.User} class
 */
public interface UserRepository {
    User save(User user);

    // return false if not found
    boolean delete(Long id);

    // return null if not found
    User get(Long id);

    // return null if not found
    User getByEmail(String email);

    // return empty list if not found
    List<User> findByNameAndSurname(String name, String surname);

    List<User> getUsers(List<Long> ids);
}
