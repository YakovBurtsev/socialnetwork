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
    public boolean cancel(Long id) {
        return repository.delete(id);
    }

    @Override
    public boolean accept(Long id) {
        return repository.delete(id);
    }

    @Override
    public boolean reject(Long id) {
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
}
