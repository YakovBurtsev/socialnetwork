package ru.yakovburtsev.socialnetwork.user.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.service.RegistrationService;
import ru.yakovburtsev.socialnetwork.core.service.UserService;
import ru.yakovburtsev.socialnetwork.core.util.exception.RegistrationException;
import ru.yakovburtsev.socialnetwork.core.util.exception.NotFoundException;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserService userService;

    @Override
    public boolean register(String name, String surname, String email, String password) throws RegistrationException {
        try {
            userService.getByEmail(email);
            throw new RegistrationException("User with email: " + email + " already registered!");
        } catch (NotFoundException e) {
            User user = new User();
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPassword(password);
            userService.save(user);
            return true;
        }
    }
}
