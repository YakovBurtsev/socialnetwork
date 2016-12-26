package ru.yakovburtsev.socialnetwork.friends.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.yakovburtsev.socialnetwork.core.model.Request;
import ru.yakovburtsev.socialnetwork.core.service.RequestService;
import ru.yakovburtsev.socialnetwork.friends.repository.RequestRepository;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;


    @Autowired
    public RequestServiceImpl(RequestRepository repository) {
        this.repository = repository;
    }

    @Override
    public Request create(Request request) {
        Assert.notNull(request, "request must not be null");
        return repository.save(request);
    }

    @Override
    public Request get(Long id) {
        return repository.get(id);
    }

    @Override
    public boolean delete(Long id) {
        return repository.delete(id);
    }

    @Override
    public List<Request> getSentRequests(Long userId) {
        return repository.getSentRequests(userId);
    }

    @Override
    public List<Request> getReceivedRequests(Long userId) {
        return repository.getReceiveRequests(userId);
    }

    @Override
    public boolean isSent(Long fromId, Long toId) {
        return repository.isSent(fromId, toId);
    }
}
