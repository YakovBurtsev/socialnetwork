package ru.yakovburtsev.socialnetwork.user.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.service.UserService;
import ru.yakovburtsev.socialnetwork.user.config.TestConfig;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

    @Test(expected = DataIntegrityViolationException.class)
    public void testDuplicateMailSave() throws Exception {
        User duplicate = new User.Builder().name("name").surname("surname").email(IVAN.getEmail()).password("password").build();
        service.save(duplicate);
    }

    @Test
    public void testDelete() throws Exception {
        assertTrue(service.delete(PETR_ID));
    }

    @Test
    public void testNotFoundDelete() throws Exception {
        assertFalse(service.delete(START_SEQ - 1));
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

    @Test
    public void testFindByNameAndSurname() throws Exception {
        MATCHER.assertCollectionEquals(Collections.singletonList(VASILIY), service.findByNameAndSurname("Vasiliy", "Ivanov"));
    }

    @Test
    public void testFindByName() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(VASILIY, OTHER_VASILIY), service.findByNameAndSurname("Vasiliy", ""));
    }

    @Test
    public void testFindBySurname() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(IVAN, VASILIY), service.findByNameAndSurname("", "Ivanov"));
    }

    @Test
    public void testNotFindByNameAndSurname() throws Exception {
        MATCHER.assertCollectionEquals(Collections.emptyList(), service.findByNameAndSurname("NotExists", "NotExists"));
    }

    @Test
    public void testFindAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(IVAN, PETR, VASILIY, OTHER_VASILIY), service.findByNameAndSurname("", ""));
    }

}