package ru.yakovburtsev.socialnetwork.friends.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yakovburtsev.socialnetwork.core.model.FriendInfo;
import ru.yakovburtsev.socialnetwork.core.service.FriendsService;
import ru.yakovburtsev.socialnetwork.friends.repository.FriendsRepository;

import java.util.List;

/**
 * The class is implementation of {@link ru.yakovburtsev.socialnetwork.core.service.FriendsService} interface
 */
@Service
public class FriendsServiceImpl implements FriendsService {
    private final FriendsRepository repository;

    @Autowired
    public FriendsServiceImpl(FriendsRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FriendInfo> getFriends(Long userId) {
        return repository.getFriends(userId);
    }

    @Override
    public boolean addFriend(Long userId, Long friendId) {
        return repository.addFriend(userId, friendId);
    }

    @Override
    public boolean deleteFromFriends(Long userId, Long friendId) {
        return repository.deleteFromFriends(userId, friendId);
    }

}
