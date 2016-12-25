package ru.yakovburtsev.socialnetwork.core.service;

import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;

import java.util.List;

/**
 * The interface defines business logic of managing by objects of {@link User} class.
 */

public interface UserService {
    User save(User user);

    void delete(Long id);

    // null if not found
    User get(Long id);

    // null if not found
    User getByEmail(String email);

    void update(User user);

    // Returns empty list if not found
    List<UserInfo> findByNameAndSurname(String name, String surname);

}
