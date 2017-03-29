package ru.yakovburtsev.socialnetwork.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.yakovburtsev.socialnetwork.core.exception.DuplicateEmailException;
import ru.yakovburtsev.socialnetwork.core.exception.UserNotFoundException;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;
import ru.yakovburtsev.socialnetwork.core.service.UserService;
import ru.yakovburtsev.socialnetwork.user.repository.UserRepository;

import java.util.List;
import java.util.Objects;

import static ru.yakovburtsev.socialnetwork.user.util.PasswordUtil.encode;
import static ru.yakovburtsev.socialnetwork.user.util.UserInfoUtil.toUserInfoList;

/**
 * This class is implementation of {@link UserService} interface.
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        log.info("save user {}", user);
        Assert.notNull(user, "user must not be null");
        try {
            return repository.save(prepareToSave(user));
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        log.info("delete user id={}", id);
        boolean deleted = repository.delete(id);
        if (!deleted) {
            throw new UserNotFoundException("Not found user with id=" + id);
        }
    }

    @Override
    public User get(Long id) {
        log.info("get user id={}", id);
        User user = repository.get(id);
        if (user == null) {
            throw new UserNotFoundException("Not found user with id=" + id);
        }
        log.info("got: {}", user);
        return user;
    }

    @Override
    public User getByEmail(String email) {
        log.info("get user by email={}", email);
        Assert.notNull(email, "email must not be null");
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UserNotFoundException("Not found user with email=" + email);
        }
        log.info("got: {}", user);
        return user;
    }

    @Override
    public void update(User user) {
        log.info("update user {}", user);
        Assert.notNull(user, "user must not be null");
        repository.save(prepareToSave(user));
    }

    @Override
    public List<UserInfo> findByNameAndSurname(String name, String surname) {
        log.info("find users by name={} and surname={}", name, surname);
        List<UserInfo> userInfos = toUserInfoList(repository.findByNameAndSurname(
                Objects.toString(name, ""),
                Objects.toString(surname, ""))
        );
        log.info("got: {}", userInfos);
        return userInfos;
    }

    @Override
    public List<UserInfo> getUsers(List<Long> ids) {
        log.info("get users where id in {}", ids);
        List<UserInfo> userInfos = toUserInfoList(repository.getUsers(ids));
        log.info("got: {}", userInfos);
        return userInfos;
    }

    private static User prepareToSave(User user) {
        user.setPassword(encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
