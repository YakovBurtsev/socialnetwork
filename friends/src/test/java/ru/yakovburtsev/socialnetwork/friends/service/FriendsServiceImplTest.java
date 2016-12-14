package ru.yakovburtsev.socialnetwork.friends.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import ru.yakovburtsev.socialnetwork.core.service.FriendsService;
import ru.yakovburtsev.socialnetwork.friends.config.TestConfig;

import java.util.Arrays;
import java.util.Collections;

import static ru.yakovburtsev.socialnetwork.friends.FriendsTestData.*;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class FriendsServiceImplTest {

    @Autowired
    protected FriendsService service;

    @Test
    public void getFriends() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(PETR, VASILIY, VITALIY, OLGA), service.getFriends(IVAN_ID));
    }

    @Test
    public void addFriend() throws Exception {
        Assert.isTrue(service.addFriend(VASILIY_ID, PETR_ID));
        MATCHER.assertCollectionEquals(Arrays.asList(IVAN, PETR), service.getFriends(VASILIY_ID));
        MATCHER.assertCollectionEquals(Arrays.asList(IVAN, VASILIY), service.getFriends(PETR_ID));
    }

    @Test
    public void deleteFromFriends() throws Exception {
        Assert.isTrue(service.deleteFromFriends(VITALIY_ID, OLGA_ID));
        MATCHER.assertCollectionEquals(Collections.singletonList(IVAN), service.getFriends(VITALIY_ID));
        MATCHER.assertCollectionEquals(Collections.singletonList(IVAN), service.getFriends(OLGA_ID));
    }
}