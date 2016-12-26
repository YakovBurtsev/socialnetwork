package ru.yakovburtsev.socialnetwork.friends.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.yakovburtsev.socialnetwork.core.model.Request;
import ru.yakovburtsev.socialnetwork.core.service.RequestService;
import ru.yakovburtsev.socialnetwork.friends.config.TestConfig;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertTrue;
import static ru.yakovburtsev.socialnetwork.friends.RequestTestData.*;

@ContextConfiguration(classes = TestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RequestServiceImplTest {

    @Autowired
    private RequestService service;

    @Test
    public void create() throws Exception {
        Request newRequest = new Request(10L, 11L);
        Request created = service.create(newRequest);
        newRequest.setId(created.getId());
        MATCHER.assertEquals(newRequest, service.get(created.getId()));
    }

    @Test
    public void cancel() throws Exception {
        assertTrue(service.delete(REQUEST_0_1.getId()));
    }

    @Test
    public void getSentRequests() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(REQUEST_3_4, REQUEST_3_5),
                service.getSentRequests(REQUEST_3_4.getFromUserId()));
    }

    @Test
    public void getReceivedRequests() throws Exception {
        MATCHER.assertCollectionEquals(Collections.singleton(REQUEST_0_2),
                service.getReceivedRequests(REQUEST_0_2.getToUserId()));
    }

}