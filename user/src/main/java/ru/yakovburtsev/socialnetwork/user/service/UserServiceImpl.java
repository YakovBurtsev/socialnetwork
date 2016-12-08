package ru.yakovburtsev.socialnetwork.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.service.*;
import ru.yakovburtsev.socialnetwork.core.util.ExceptionUtil;
import ru.yakovburtsev.socialnetwork.core.util.exception.NotFoundException;
import ru.yakovburtsev.socialnetwork.user.repository.UserRepository;

import java.util.List;

import static ru.yakovburtsev.socialnetwork.user.util.UserUtil.prepareToSave;

/**
 * This class is implementation of {@link UserService} interface.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Override
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(prepareToSave(user));
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(Long id) throws NotFoundException {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return ExceptionUtil.checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        repository.save(prepareToSave(user));
    }

    @Override
    public List<User> findUser(User lookingFor) {
        //TODO
        return null;
    }
}
