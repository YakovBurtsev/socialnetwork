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

import static ru.yakovburtsev.socialnetwork.user.util.UserInfoUtil.toUserInfoList;
import static ru.yakovburtsev.socialnetwork.user.util.UserUtil.prepareToSave;

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
        Assert.notNull(name, "name must not be null");
        Assert.notNull(surname, "surname must not be null");
        List<User> users = repository.findByNameAndSurname(name, surname);
        return toUserInfoList(users);
    }
}
