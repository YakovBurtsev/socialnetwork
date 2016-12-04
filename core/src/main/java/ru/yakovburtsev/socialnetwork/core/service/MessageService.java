package ru.yakovburtsev.socialnetwork.core.service;

import ru.yakovburtsev.socialnetwork.core.model.Message;

import java.util.List;

/**
 * The interface defines business logic of managing by objects of {@link Message} class.
 */

public interface MessageService {
    void create(Message message);

    Message get(Long id);

    void update(Message message);

    void delete(Long id);

    List<Message> getReceivedMessages(Long userId);

    List<Message> getSentMessages(Long userId);
}
