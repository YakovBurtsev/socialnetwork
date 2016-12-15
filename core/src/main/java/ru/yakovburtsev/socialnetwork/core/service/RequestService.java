package ru.yakovburtsev.socialnetwork.core.service;

import ru.yakovburtsev.socialnetwork.core.model.Request;

import java.util.List;

/**
 * The interface for managing by request to add a friend.
 * One user send a request to another. And the another can accept or reject it.
 * Also a user can cancel a request.
 */
public interface RequestService {
    Request create(Request request);

    Request get(Long id);

    boolean cancel(Long id);

    boolean accept(Long id);

    boolean reject(Long id);

    List<Request> getSentRequests(Long userId);

    List<Request> getReceivedRequests(Long userId);
}
