package ru.yakovburtsev.socialnetwork.user.repository;


import ru.yakovburtsev.socialnetwork.core.model.User;

/**
 * The interface define data access objects methods for instances of
 * {@link ru.yakovburtsev.socialnetwork.core.model.User} class
 */
public interface UserRepository {
    User save(User user);

    // false if not found
    boolean delete(Long id);

    // null if not found
    User get(Long id);

    // null if not found
    User getByEmail(String email);
}
