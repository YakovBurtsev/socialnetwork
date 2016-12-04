package ru.yakovburtsev.socialnetwork.core.service;

import ru.yakovburtsev.socialnetwork.core.model.User;

import java.util.List;

/**
 * The interface defines service of managing by friends. User can get friends list, add a new friend or delete someone.
 */
public interface FriendsService {
    List<User> getFriends(Long userId);

    void addFriend(Long userId, Long friendId);

    void deleteFromFriends(Long userId, Long friendId);

}
