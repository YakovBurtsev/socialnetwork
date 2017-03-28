package ru.yakovburtsev.socialnetwork.friends.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;
import ru.yakovburtsev.socialnetwork.core.service.FriendsService;
import ru.yakovburtsev.socialnetwork.friends.repository.FriendsRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static ru.yakovburtsev.socialnetwork.friends.config.MessagingConfig.USER_QUEUE;
import static ru.yakovburtsev.socialnetwork.friends.config.MessagingConfig.USER_RESPONSE_QUEUE;

/**
 * The class is implementation of {@link ru.yakovburtsev.socialnetwork.core.service.FriendsService} interface
 */
@Slf4j
@Service
@Transactional(readOnly = true)
public class FriendsServiceImpl implements FriendsService {

    private final FriendsRepository repository;
    private final JmsTemplate jmsTemplate;

    @Autowired
    public FriendsServiceImpl(FriendsRepository repository, JmsTemplate jmsTemplate) {
        this.repository = repository;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<UserInfo> getFriends(Long userId) {
        log.info("get friends of userId={}", userId);
        List<Long> friendsIds = repository.getFriendsIds(userId);
        List<UserInfo> userInfos = emptyList();
        if (!friendsIds.isEmpty()) {
            jmsTemplate.send(USER_QUEUE, session -> session.createObjectMessage(new ArrayList<>(friendsIds)));
            userInfos = (List<UserInfo>) jmsTemplate.receiveAndConvert(USER_RESPONSE_QUEUE);
        }
        log.info("got: {}", userInfos);
        return userInfos;
    }

    @Override
    public boolean isFriend(Long userId, Long friendId) {
        log.info("check whether there is userId={} in friend-list of userId={}", friendId, userId);
        boolean isFriend = repository.isFriend(userId, friendId);
        log.info("Is it a friend={}", isFriend);
        return isFriend;
    }

    @Override
    @Transactional
    public boolean addFriend(Long userId, Long friendId) {
        log.info("add userId={} to friends of userId={}", friendId, userId);
        return repository.addFriend(userId, friendId);
    }

    @Override
    @Transactional
    public boolean deleteFromFriends(Long userId, Long friendId) {
        log.info("delete userId={} from friends userId={}", friendId, userId);
        return repository.deleteFromFriends(userId, friendId);
    }

}
