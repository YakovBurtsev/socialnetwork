package ru.yakovburtsev.socialnetwork.core.service;

import ru.yakovburtsev.socialnetwork.core.model.UserInfo;

import java.util.List;

/**
 * The interface defines service of managing by friends. User can get friends list, add a new friend or delete someone.
 */
public interface FriendsService {
    List<UserInfo> getFriends(Long userId);

    boolean isFriend(Long userId, Long friendId);

    // Returns true if success
    boolean addFriend(Long userId, Long friendId);

    // Returns true if success
    boolean deleteFromFriends(Long userId, Long friendId);

}
