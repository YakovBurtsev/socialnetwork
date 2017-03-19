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
    private FriendsService service;

    @Test
    public void isFriend() throws Exception {
        Assert.isTrue(service.isFriend(IVAN_ID, VASILIY_ID));
    }

    @Test
    public void addFriend() throws Exception {
        Assert.isTrue(service.addFriend(VASILIY_ID, PETR_ID));
    }

    @Test
    public void deleteFromFriends() throws Exception {
        Assert.isTrue(service.deleteFromFriends(VITALIY_ID, OLGA_ID));
    }
}