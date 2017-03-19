package ru.yakovburtsev.socialnetwork.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;
import ru.yakovburtsev.socialnetwork.core.service.UserService;
import ru.yakovburtsev.socialnetwork.user.repository.UserRepository;
import ru.yakovburtsev.socialnetwork.user.util.exception.UserNotFoundException;

import java.util.List;
import java.util.Objects;

import static ru.yakovburtsev.socialnetwork.user.util.PasswordUtil.encode;
import static ru.yakovburtsev.socialnetwork.user.util.UserInfoUtil.toUserInfoList;

/**
 * This class is implementation of {@link UserService} interface.
 */

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(prepareToSave(user));
    }

    @Override
    public void delete(Long id) {
        boolean deleted = repository.delete(id);
        if (!deleted) {
            throw new UserNotFoundException("Not found user with id=" + id);
        }
    }

    @Override
    public User get(Long id) {
        User user = repository.get(id);
        if (user == null) {
            throw new UserNotFoundException("Not found user with id=" + id);
        }
        return user;
    }

    @Override
    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UserNotFoundException("Not found user with email=" + email);
        }
        return user;
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        repository.save(prepareToSave(user));
    }

    @Override
    public List<UserInfo> findByNameAndSurname(String name, String surname) {
        return toUserInfoList(repository.findByNameAndSurname(
                Objects.toString(name, ""),
                Objects.toString(surname, ""))
        );
    }

    @Override
    public List<UserInfo> getUsers(List<Long> ids) {
        return toUserInfoList(repository.getUsers(ids));
    }

    private static User prepareToSave(User user) {
        user.setPassword(encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
