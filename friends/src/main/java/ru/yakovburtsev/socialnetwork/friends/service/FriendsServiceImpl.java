package ru.yakovburtsev.socialnetwork.friends.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yakovburtsev.socialnetwork.core.model.UserInfo;
import ru.yakovburtsev.socialnetwork.core.service.FriendsService;
import ru.yakovburtsev.socialnetwork.friends.repository.FriendsRepository;

import java.util.Collections;
import java.util.List;

/**
 * The class is implementation of {@link ru.yakovburtsev.socialnetwork.core.service.FriendsService} interface
 */
@Service
@Transactional(readOnly = true)
public class FriendsServiceImpl implements FriendsService {
    private final FriendsRepository repository;

    @Autowired
    public FriendsServiceImpl(FriendsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserInfo> getFriends(Long userId) {
        List<Long> friendsIds = repository.getFriendsIds(userId);
        if (friendsIds.isEmpty()) {
            return Collections.emptyList();
        } else {
            return repository.getFriends(friendsIds);
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
