package ru.yakovburtsev.socialnetwork.user.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.service.UserService;
import ru.yakovburtsev.socialnetwork.user.config.TestConfig;

import static ru.yakovburtsev.socialnetwork.user.UserTestData.*;


@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {


    @Autowired
    protected UserService service;

    @Test
    public void testSave() throws Exception {
        User newUser = new User.Builder().name("name").surname("surname").email("new@gmail.com").password("password").build();
        User created = service.save(newUser);
        newUser.setId(created.getId());
        MATCHER.assertEquals(newUser, service.get(created.getId()));
    }

    @Test(expected = DuplicateKeyException.class)
    public void testDuplicateMailSave() throws Exception {
        User duplicate = new User.Builder().name("name").surname("surname").email(IVAN.getEmail()).password("password").build();
        service.save(duplicate);
    }

    @Test
    public void testDelete() throws Exception {
        Assert.assertTrue(service.delete(PETR_ID));
    }

    @Test
    public void testNotFoundDelete() throws Exception {
        Assert.assertFalse(service.delete(START_SEQ - 1));
    }

    @Test
    public void testGet() throws Exception {
        User user = service.get(IVAN_ID);
        MATCHER.assertEquals(IVAN, user);
    }

    @Test
    public void testGetNotFound() throws Exception {
        Assert.assertNull(service.get(START_SEQ - 1));
    }

    @Test
    public void testGetByEmail() throws Exception {
        User user = service.getByEmail(IVAN.getEmail());
        MATCHER.assertEquals(IVAN, user);
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(IVAN);
        updated.setName("UpdatedName");
        updated.setCity("UpdatedCity");
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(IVAN_ID));
    }
}