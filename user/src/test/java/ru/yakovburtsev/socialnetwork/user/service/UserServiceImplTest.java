package ru.yakovburtsev.socialnetwork.user.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yakovburtsev.socialnetwork.core.exception.DuplicateEmailException;
import ru.yakovburtsev.socialnetwork.core.exception.UserNotFoundException;
import ru.yakovburtsev.socialnetwork.core.model.Role;
import ru.yakovburtsev.socialnetwork.core.model.User;
import ru.yakovburtsev.socialnetwork.core.service.UserService;
import ru.yakovburtsev.socialnetwork.user.config.TestConfig;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static ru.yakovburtsev.socialnetwork.user.UserTestData.IVAN;
import static ru.yakovburtsev.socialnetwork.user.UserTestData.IVAN_ID;
import static ru.yakovburtsev.socialnetwork.user.UserTestData.OTHER_VASILIY;
import static ru.yakovburtsev.socialnetwork.user.UserTestData.PETR;
import static ru.yakovburtsev.socialnetwork.user.UserTestData.PETR_ID;
import static ru.yakovburtsev.socialnetwork.user.UserTestData.START_SEQ;
import static ru.yakovburtsev.socialnetwork.user.UserTestData.USER_INFO_MATCHER;
import static ru.yakovburtsev.socialnetwork.user.UserTestData.USER_MATCHER;
import static ru.yakovburtsev.socialnetwork.user.UserTestData.VASILIY;
import static ru.yakovburtsev.socialnetwork.user.util.UserInfoUtil.toUserInfoList;


@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Autowired
    private UserService service;

    @Test
    public void testSave() {
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

    @Test(expected = DuplicateEmailException.class)
    public void testDuplicateMailSave() {
        User duplicate = new User.Builder()
                .name("name")
                .surname("surname")
                .email(IVAN.getEmail())
                .password("password")
                .build();
        service.save(duplicate);
    }

    @Test
    public void testDelete() {
        service.delete(PETR_ID);
    }

    @Test(expected = UserNotFoundException.class)
    public void testNotFoundDelete() {
        service.delete(START_SEQ - 1);
    }

    @Test
    public void testGet() {
        User user = service.get(IVAN_ID);
        USER_MATCHER.assertEquals(IVAN, user);
    }

    @Test(expected = UserNotFoundException.class)
    public void testNotFound() {
        Assert.assertNull(service.get(START_SEQ - 1));
    }

    @Test
    public void testGetByEmail() {
        User user = service.getByEmail(IVAN.getEmail());
        USER_MATCHER.assertEquals(IVAN, user);
    }

    @Test(expected = UserNotFoundException.class)
    public void testNotFoundByEmail() {
        service.getByEmail("not-exist-email");
    }

    @Test
    public void testUpdate() {
        User updated = new User(IVAN);
        updated.setName("UpdatedName");
        updated.setCity("UpdatedCity");
        service.update(updated);
        USER_MATCHER.assertEquals(updated, service.get(IVAN_ID));
    }

    @Test
    public void testFindByNameAndSurname() {
        USER_INFO_MATCHER.assertCollectionEquals(
                toUserInfoList(singletonList(VASILIY)),
                service.findByNameAndSurname("Vasiliy", "Ivanov")
        );
    }

    @Test
    public void testFindByName() {
        USER_INFO_MATCHER.assertCollectionEquals(
                toUserInfoList(asList(VASILIY, OTHER_VASILIY)),
                service.findByNameAndSurname("Vasiliy", "")
        );
    }

    @Test
    public void testFindBySurname() {
        USER_INFO_MATCHER.assertCollectionEquals(
                toUserInfoList(asList(IVAN, VASILIY)),
                service.findByNameAndSurname("", "Ivanov")
        );
    }

    @Test
    public void testNotFindByNameAndSurname() {
        USER_INFO_MATCHER.assertCollectionEquals(
                emptyList(),
                service.findByNameAndSurname("NotExists", "NotExists")
        );
    }

    @Test
    public void testFindAll() {
        USER_INFO_MATCHER.assertCollectionEquals(
                toUserInfoList(asList(IVAN, PETR, VASILIY, OTHER_VASILIY)),
                service.findByNameAndSurname("", "")
        );
    }
}