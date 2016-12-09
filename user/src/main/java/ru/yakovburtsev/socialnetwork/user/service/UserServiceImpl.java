package ru.yakovburtsev.socialnetwork.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.service.UserService;
import ru.yakovburtsev.socialnetwork.user.repository.UserRepository;

import java.util.Collections;
import java.util.List;

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
    public boolean delete(Long id) {
        return repository.delete(id);
    }

    @Override
    public User get(Long id) {
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return repository.getByEmail(email);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        repository.save(prepareToSave(user));
    }

    @Override
    public List<User> findUser(User lookingFor) {
        //TODO реализовать поиск юзера
        return Collections.emptyList();
    }
}
