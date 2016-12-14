package ru.yakovburtsev.socialnetwork.friends.repository;

import ru.yakovburtsev.socialnetwork.core.model.FriendInfo;

import java.util.List;

/**
 * The interface define data access objects methods for working with a user friends
 */
public interface FriendsRepository {

    /**
     * Return a list with information (id, name and surname) about friends of a users
     * @param userId id of a user
     * @return list of info about friends
     */
    List<FriendInfo> getFriends(Long userId);

    // true if success
    boolean addFriend(Long userId, Long friendId);

    // true if success
    boolean deleteFromFriends(Long userId, Long friendId);
}
