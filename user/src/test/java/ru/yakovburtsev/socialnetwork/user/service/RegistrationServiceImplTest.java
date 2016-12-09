package ru.yakovburtsev.socialnetwork.user.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import ru.yakovburtsev.socialnetwork.core.service.RegistrationService;
import ru.yakovburtsev.socialnetwork.core.util.exception.RegistrationException;
import ru.yakovburtsev.socialnetwork.user.config.TestConfig;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RegistrationServiceImplTest {

    @Autowired
    private RegistrationService registrationService;

    @Test
    public void register() throws Exception {
        boolean result = registrationService.register("New", "New", "new@gmail.com", "new");
        Assert.isTrue(result);
    }

    @Test(expected = RegistrationException.class)
    public void duplicateRegister() throws Exception {
        registrationService.register("New", "New", "ivan@yandex.ru", "new");
    }

    @Test(expected = IllegalArgumentException.class)
    public void registerNotValidUser() throws Exception {
        registrationService.register("New", "New", null, "new");
    }
}