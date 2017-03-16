package ru.yakovburtsev.socialnetwork.user.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yakovburtsev.socialnetwork.core.model.Role;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.service.UserService;
import ru.yakovburtsev.socialnetwork.user.config.TestConfig;
import ru.yakovburtsev.socialnetwork.user.util.exception.UserNotFoundException;

import java.util.Arrays;
import java.util.Collections;

import static ru.yakovburtsev.socialnetwork.user.UserTestData.*;
import static ru.yakovburtsev.socialnetwork.user.util.UserInfoUtil.toUserInfoList;


@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Autowired
    private UserService service;

    @Test
    public void testSave() throws Exception {
        User newUser = new User.Builder()
                .name("name")
                .surname("surname")
                .email("new@gmail.com")
                .password("password")
                .roles(Role.ROLE_USER).build();
        User created = service.save(newUser);
        newUser.setId(created.getId());
        USER_MATCHER.assertEquals(newUser, service.get(created.getId()));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testDuplicateMailSave() throws Exception {
        User duplicate = new User.Builder()
                .name("name")
                .surname("surname")
                .email(IVAN.getEmail())
                .password("password")
                .build();
        service.save(duplicate);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(PETR_ID);
    }

    @Test(expected = UserNotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(START_SEQ - 1);
    }

    @Test
    public void testGet() throws Exception {
        User user = service.get(IVAN_ID);
        USER_MATCHER.assertEquals(IVAN, user);
    }

    @Test(expected = UserNotFoundException.class)
    public void testGetNotFound() throws Exception {
        Assert.assertNull(service.get(START_SEQ - 1));
    }

    @Test
    public void testGetByEmail() throws Exception {
        User user = service.getByEmail(IVAN.getEmail());
        USER_MATCHER.assertEquals(IVAN, user);
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(IVAN);
        updated.setName("UpdatedName");
        updated.setCity("UpdatedCity");
        service.update(updated);
        USER_MATCHER.assertEquals(updated, service.get(IVAN_ID));
    }

    @Test
    public void testFindByNameAndSurname() throws Exception {
        USER_INFO_MATCHER.assertCollectionEquals(
                toUserInfoList(Collections.singletonList(VASILIY)),
                service.findByNameAndSurname("Vasiliy", "Ivanov")
        );
    }

    @Test
    public void testFindByName() throws Exception {
        USER_INFO_MATCHER.assertCollectionEquals(
                toUserInfoList(Arrays.asList(VASILIY, OTHER_VASILIY)),
                service.findByNameAndSurname("Vasiliy", "")
        );
    }

    @Test
    public void testFindBySurname() throws Exception {
        USER_INFO_MATCHER.assertCollectionEquals(
                toUserInfoList(Arrays.asList(IVAN, VASILIY)),
                service.findByNameAndSurname("", "Ivanov")
        );
    }

    @Test
    public void testNotFindByNameAndSurname() throws Exception {
        USER_INFO_MATCHER.assertCollectionEquals(
                Collections.emptyList(),
                service.findByNameAndSurname("NotExists", "NotExists")
        );
    }

    @Test
    public void testFindAll() throws Exception {
        USER_INFO_MATCHER.assertCollectionEquals(
                toUserInfoList(Arrays.asList(IVAN, PETR, VASILIY, OTHER_VASILIY)),
                service.findByNameAndSurname("", "")
        );
    }
}