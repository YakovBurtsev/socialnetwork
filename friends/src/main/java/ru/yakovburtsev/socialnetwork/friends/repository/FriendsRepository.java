package ru.yakovburtsev.socialnetwork.friends.repository;

import java.util.List;

/**
 * The interface define data access objects methods for working with a user friends
 */
public interface FriendsRepository {

    List<Long> getFriendsIds(Long userId);

    // true if success
    boolean addFriend(Long userId, Long friendId);

    // true if success
    boolean deleteFromFriends(Long userId, Long friendId);
}
