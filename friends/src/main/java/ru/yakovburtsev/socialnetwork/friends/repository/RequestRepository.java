package ru.yakovburtsev.socialnetwork.friends.repository;

import ru.yakovburtsev.socialnetwork.core.model.Request;

import java.util.List;

/**
 * The interface represents methods for persistence of a request for adding a new friend
 */
public interface RequestRepository {
    Request save(Request request);

    // return null if not found
    Request get(Long id);

    // return false if not found
    boolean delete(Long id);

    // return empty list if not found
    List<Request> getSentRequests(Long userId);

    // return empty list if not found
    List<Request> getReceiveRequests(Long userId);

}
