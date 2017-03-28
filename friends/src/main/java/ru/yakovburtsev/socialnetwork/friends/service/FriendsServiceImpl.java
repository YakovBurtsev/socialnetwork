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
    @SuppressWarnings("uncheked")
    public List<UserInfo> getFriends(Long userId) {
        List<Long> friendsIds = repository.getFriendsIds(userId);
        if (friendsIds.isEmpty()) {
            return emptyList();
        } else {
            jmsTemplate.send(USER_QUEUE, session -> session.createObjectMessage(new ArrayList<>(friendsIds)));
            return (List<UserInfo>) jmsTemplate.receiveAndConvert(USER_RESPONSE_QUEUE);
        }
    }

    @Override
    public boolean isFriend(Long userId, Long friendId) {
        return repository.isFriend(userId, friendId);
    }

    @Override
    @Transactional
    public boolean addFriend(Long userId, Long friendId) {
        return repository.addFriend(userId, friendId);
    }

    @Override
    @Transactional
    public boolean deleteFromFriends(Long userId, Long friendId) {
        return repository.deleteFromFriends(userId, friendId);
    }

}
